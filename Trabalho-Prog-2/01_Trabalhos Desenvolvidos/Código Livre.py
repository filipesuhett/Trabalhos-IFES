#-------------------------------------------#
# TRABALHO DE PROGRAMAÇÃO II                #
# ALUNOS: FILIPE SUHETT E GIOVANNA SCALFONI #
# PROFESSOR: HILÁRIO SEIBEL JR              #
# OBS: Código Alterado para ser Otimizado   #
#-------------------------------------------#

#-------------------#
# Imports do Código #
#-------------------#
import pickle

#--------------------#
# Ordenação de Merge #
#--------------------#

# Usando o merge para fazer as divisões e ordenar
def ordena(lista):
    if len(lista) > 1:
        tam = len(lista)//2
        direita = lista[:tam]
        esquerda = lista[tam:]

        ordena(direita)
        ordena(esquerda)
        
        var1 = 0
        var2 = 0
        var3 = 0
        
        # FAZENDO AS COMPARAÇÕES #
        
        while len(esquerda) > var2 and var1 < len(direita):
            
            # Comparação de Nota com Ponto Extra
            if esquerda[var2][0] != direita[var1][0]:
                if esquerda[var2][0] > direita[var1][0]:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1
            
            # Comparação de Nota sem Ponto Extra 
            elif esquerda[var2][1] != direita[var1][1]:
                if esquerda[var2][1] < direita[var1][1]:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1
            
            # Comparação de ano que cursou
            elif esquerda[var2][2] != direita[var1][2]:
                if esquerda[var2][2] > direita[var1][2]:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1
            
            # Comparação do semestre que cursou 
            elif esquerda[var2][3] != direita[var1][3]:
                if esquerda[var2][3] > direita[var1][3]:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1        
            
            # Comparação do nome
            elif esquerda[var2][4] != direita[var1][4]:
                lista1 = [esquerda[var2][4], direita[var1][4]]
                lista2 = sorted(lista1)
                if lista1 == lista2:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1
            
            # Comparação da Matrícula
            else:
                lista1 = [esquerda[var2][5], direita[var1][5]]
                lista2 = sorted(lista1)
                if lista1 == lista2:
                    lista[var3] = esquerda[var2]
                    var2 += 1
                else:
                    lista[var3] = direita[var1]
                    var1 += 1
                var3 += 1        
            
        while var2 < len(esquerda):
            lista[var3] = esquerda[var2]
            var3 += 1
            var2 += 1
            
        while var1 < len(direita):
            lista[var3] = direita[var1]
            var3 += 1
            var1 += 1

#@--------------------#
#@ Lista para ordenar #
#@--------------------#   

def nota(dic):
    
    # Definindo os valores
    dados_dos_alunos = []
    cont = 0
    indi_aluno = ()
    nota_string = 0
    
    # lista auxiliar
    x = list(dic.items())
    
    # Calculando as notas com os pontos extras e Criando Lista auxiliar
    while len(x) > cont:
        nota_aluno = sum(x[cont][1][2])
        if x[cont][1][3] == 0 and nota_aluno <= 98:
            nota_string = nota_aluno
            nota_aluno = nota_aluno + 2
            indi_aluno = (nota_aluno, 2, x[cont][1][1][0], x[cont][1][1][1], x[cont][1][0], x[cont][0], str(nota_string)+' +2')
        elif x[cont][1][3] == 0 and nota_aluno == 99:
            nota_string = nota_aluno
            nota_aluno = nota_aluno + 1
            indi_aluno = (nota_aluno, 1, x[cont][1][1][0], x[cont][1][1][1], x[cont][1][0], x[cont][0], str(nota_string)+' +1')
        else:
            indi_aluno = (nota_aluno, 0, x[cont][1][1][0], x[cont][1][1][1], x[cont][1][0], x[cont][0], str(nota_aluno))
        dados_dos_alunos.append(indi_aluno)
        cont += 1
    
    # Chamando o MergeSort
    ordena(dados_dos_alunos)
    return dados_dos_alunos

#---------------#
# Busca Binária #
#---------------#
def pesquisa_binaria(A, item):
    ini, fim = 0, len(A) - 1
    while ini <= fim:
        meio = (ini + fim) // 2
        if A[meio] == item and A[meio+1] < 60:
            return meio
        elif A[meio] >= item:
            ini = meio + 1
        else:
            fim = meio - 1
    return -1

#-------------------------#
# For auxiliar da Binária #
#-------------------------#

def auxlista(dados_dos_alunos):
    aux = []
    
    for i in dados_dos_alunos:
        aux.append(i[0])
    return aux
    
#----------------------#
# Transformação em Txt #
#----------------------#
def escreverArquivo(dados_dos_alunos):
    cont = 0
    
    # Usando a lista ordenada para fazer o txt ordenado
    with open('saida.txt' , 'w', encoding=' utf8') as f:
        
        # While para fazermos o txt
        while cont < len(dados_dos_alunos):
            f.write(dados_dos_alunos[cont][4]+' - '+dados_dos_alunos[cont][6])
            f.write('\n')
            cont += 1

#@-----------------------------------------#
#@ Função Main: Transformação do Bin e Txt #
#@-----------------------------------------#

def main():
    
    # Abrindo o Arquivo.bin e transformando em Dicionário
    with open ('entrada.bin', 'rb') as u:
        dic = pickle.load(u)
    dados_dos_alunos = nota(dic)

    # Função para transformação do dicionário em lista e Ordenação da lista
    nota(dic)
    
    # Escrevendo arquivo txt
    escreverArquivo(dados_dos_alunos)
    
    # Auxiliar da Busca Binária
    aux = auxlista(dados_dos_alunos)
    
    # Busca Binária
    print(pesquisa_binaria(aux, 60)+1)
    
if __name__ == '__main__':
    main()