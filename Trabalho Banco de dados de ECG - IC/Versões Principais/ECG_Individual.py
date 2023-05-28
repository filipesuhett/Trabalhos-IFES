import wfdb
import numpy as np
from sklearn.model_selection import KFold
from sklearn.linear_model import LogisticRegression


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

    # Calcula os intervalos RR
    intervalos_rr = np.diff(locs_batimentos)

    # Normaliza os intervalos RR
    intervalos_rr_norm = (intervalos_rr - np.mean(intervalos_rr)) / np.std(intervalos_rr)
    
    return intervalos_rr_norm, anotacoes, idx_batimentos


def rr_calcula(intervalos_rr_norm, anotacoes, idx_batimentos):
    # Definir as variáveis de entrada (features) e saída (labels)
    X = intervalos_rr_norm[:-1].reshape(-1, 1)  # entrada é o intervalo RR normalizado, exceto para o último batimento
    y = ['A' if a != 'N' else 'N' for a in np.array(anotacoes.symbol)[idx_batimentos[1:]]]  # saída é 'A' para todos os batimentos exceto 'N'
    
    return X, y

def classificar(X, y):
    # Inicializar o modelo de classificação
    modelo = LogisticRegression()

    if len(set(y)) < 2:
        print("Erro: A variável y deve conter pelo menos duas classes distintas.")
        return -1
    
    # Definir a estratégia de K-fold cross-validation
    n_splits = 100
    kf = KFold(n_splits=n_splits, shuffle=True)

    # Realizar a validação cruzada K-fold
    scores = []
    for train_idx, test_idx in kf.split(X):
        # Dividir os dados em treino e teste de acordo com os índices gerados pelo K-fold
        X_treino, X_teste = X[train_idx], X[test_idx]
        y_treino, y_teste = np.array(y)[train_idx], np.array(y)[test_idx]
        
        # Verificar se há mais de uma classe presente nos dados de treino
        if len(set(y_treino)) < 2:
            print("Erro: Os dados de treino devem conter pelo menos duas classes distintas.")
            return -1
        
        # Treinar o modelo com os dados de treino
        modelo.fit(X_treino, y_treino)
        
        # Avaliar o modelo com os dados de teste e calcular a acurácia
        score = modelo.score(X_teste, y_teste)
        scores.append(score)

    # Imprimir a acurácia média e o desvio padrão da validação cruzada
    print("Acurácia média:", np.mean(scores))
    print("Desvio padrão:", np.std(scores))

    return np.mean(scores)

    
def main():
    # Lista com os nomes dos registros a serem processados
    nomes_registros = ['100', '101', '102', '103', '104', '105', '106', '107', '108', '109', '111', '112', '113', '114', '115', '116', '117', '118', '119', '121', '122', '123', '124', '200', '201', '202', '203', '205', '207', '208', '209', '210', '212', '213', '214', '215', '217', '219', '220', '221', '222', '223', '228', '230', '231', '232', '233', '234']
    
    # Variável para contar o número de ECGs processados
    cont = 1
    aux = 0

    # Variável da soma de todas as Acurácias Médias
    acerto_geral = 0
    
    # Loop sobre os nomes dos registros a serem processados
    for nome_registro in nomes_registros:
    
        # Chama a função processa para pré-processar os dados do registro
        rr_intervals_norm, ann, idx_batimentos = processa(nome_registro)
    
        # Chama a função rr_calcula para extrair as características dos batimentos    
        X, y = rr_calcula(rr_intervals_norm, ann, idx_batimentos)

        # Imprime o número do ECG
        print(f'ECG {nome_registro}')
        print()
        
        # Chama a função classificar para classificar os batimentos em Normal ou Anormal
        acerto = classificar(X, y)

        if acerto != -1:
          acerto_geral += acerto
          aux += 1
        
        print()
        
        cont += 1

    print('---------------------------------------------------------------------')
    print()
    print("Acurácia Geral: ", acerto_geral/aux)
    
    return 0
    
if '__main__' == __name__:
    main()