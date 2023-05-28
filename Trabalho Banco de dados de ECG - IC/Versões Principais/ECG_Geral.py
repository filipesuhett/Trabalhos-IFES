import wfdb
import numpy as np
from sklearn.model_selection import KFold
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report


def processa(nome_registro):
    # Carrega o sinal do ECG
    registro = wfdb.rdrecord('Data/' + nome_registro)

    # Extrai as anotações dos batimentos
    anotacoes = wfdb.rdann('Data/' + nome_registro, 'atr')

    # Obtém os índices dos batimentos desejados
    batimentos_desejados = ['a', 'V', 'J', 'A', 'S', 'N']
    idx_batimentos = [i for i, b in enumerate(anotacoes.symbol) if b in batimentos_desejados]

    # Obtém as localizações dos picos dos batimentos desejados
    locs_batimentos = anotacoes.sample[idx_batimentos]

    # Cria uma nova lista de anotações substituindo os batimentos não N por A
    novas_anotacoes = ['A' if anotacoes.symbol[i] != 'N' else 'N' for i in idx_batimentos]
    
    aux = len(novas_anotacoes)
    
    novas_anotacoes.pop(aux-1)
    novas_anotacoes.pop(0)

    # Calcula os intervalos RR
    intervalos_rr_norm = np.diff(locs_batimentos)
    
    amplitudes_qrs = registro.p_signal[idx_batimentos, 0]
    
    rmssd = np.sqrt(np.mean(np.square(np.diff(intervalos_rr_norm))))
    
    frequencia_cardiaca = 60 / np.mean(intervalos_rr_norm)
    
    amplitudes_qrs = amplitudes_qrs[:-1]; amplitudes_qrs
    
    rmssd = np.full(len(intervalos_rr_norm), rmssd)
    
    frequencia_cardiaca = np.full(len(intervalos_rr_norm), frequencia_cardiaca)

    # Normaliza os intervalos RR
    #intervalos_rr_norm = (intervalos_rr - np.mean(intervalos_rr)) / np.std(intervalos_rr)
    
    return intervalos_rr_norm, amplitudes_qrs, novas_anotacoes, rmssd, frequencia_cardiaca


def rr_calcula(intervalos_rr_norm, anotacoes, X_R, y_R, amplitudes_qrs, rmssd, frequencia_cardiaca):
    # Definir as variáveis de entrada (features) e saída (labels)
    X = np.concatenate((intervalos_rr_norm[:-1].reshape(-1, 1), amplitudes_qrs[:-1].reshape(-1, 1), rmssd[:-1].reshape(-1, 1), frequencia_cardiaca[:-1].reshape(-1, 1)), axis=1)
    y = np.array(anotacoes)
    X_R.extend(X)
    y_R.extend(y)
    return X_R, y_R

def normalizar_batimentos(X):
    X = np.array(X)
    X_batimentos = X[:, 0]  # Obtém apenas os batimentos para normalização
    X_batimentos_normalized = (X_batimentos - np.mean(X_batimentos)) / np.std(X_batimentos)
    X_normalized = np.column_stack((X_batimentos_normalized, X[:, 1:]))  # Mantém os outros dados inalterados
    return X_normalized

def classificar(X, y):
    # Inicializar o modelo de classificação
    modelo = RandomForestClassifier(n_estimators=10)

    if len(set(y)) < 2:
        print("Erro: A variável y deve conter pelo menos duas classes distintas.")
        return -1
    
    # Dividir os dados em treino e teste
    X_treino, X_teste, y_treino, y_teste = train_test_split(X, y, test_size=0.2, stratify=y)

    # Treinar o modelo com os dados de treino
    modelo.fit(X_treino, y_treino)
    
    # Avaliar o modelo com os dados de validação e calcular a acurácia
    score = modelo.score(X_teste, y_teste)

    # Imprimir o relatório de classificação e a acurácia
    y_pred = modelo.predict(X_teste)
    print(classification_report(y_teste, y_pred))
    print("Acurácia:", score)

    
def main():
    # Lista com os nomes dos registros a serem processados
    nomes_registros = ['100', '101', '102', '103', '104', '105', '106', '108', '112', '113', '114', '116', '119', '121', '123', '200', '201', '202', '203', '205', '208', '209', '210', '213', '215', '217', '219', '220', '221', '222', '223', '228', '231', '233', '234']    
    
    X_R = []
    y_R = []

    # Loop sobre os nomes dos registros a serem processados
    for nome_registro in nomes_registros:
        # Chama a função processa para pré-processar os dados do registro
        rr_intervals_norm, amplitudes_qrs, ann, rmssd, frequencia_cardiaca = processa(nome_registro)
        
        # Chama a função rr_calcula para extrair as características dos batimentos    
        rr_calcula(rr_intervals_norm, ann, X_R, y_R, amplitudes_qrs, rmssd, frequencia_cardiaca)

    X_R = normalizar_batimentos(X_R)

    classificar(X_R, y_R)
    
    return 0
    
if '__main__' == __name__:
    main()