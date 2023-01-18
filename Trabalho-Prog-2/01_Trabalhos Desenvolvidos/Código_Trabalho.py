#------------------------------------------#
#TRABALHO DE PROGRAMAÇÃO II                #
#ALUNOS: FILIPE SUHETT E GIOVANNA SCALFONI #
#PROFESSOR: HILÁRIO SEIBEL JR              #
#------------------------------------------#

#-------------------#
# Imports do Código #
#-------------------#
import pickle

#--------------------#
# Ordenação de Merge #
#--------------------#
def mergesort(m1, m2, dic):
    
    # Definindo os valores
    nome1, semestre1, nota1, falta1 = dic[m1]
    nome2, semestre2, nota2, falta2 = dic[m2]
    
    nota_aluno1 = sum(nota1)
    nota_aluno2 = sum(nota2)

    # Calculando as notas com os pontos extras
    if falta1 == 0 and nota_aluno1 <= 98:
        nota_aluno1 += 2
    elif falta1 == 0 and nota_aluno1 == 99:
        nota_aluno1 += 1
        
    if falta2 == 0 and nota_aluno2 <= 98:
        nota_aluno2 += 2
    elif falta2 == 0 and nota_aluno2 == 99:
        nota_aluno2 += 1     
    
    # FAZENDO AS COMPARAÇÕES #
    
    # Comparação de Nota com Ponto Extra              
    if nota_aluno1 != nota_aluno2:
        return nota_aluno1 > nota_aluno2   
    
    # Comparação de Nota sem Ponto Extra   
    elif sum(nota1) != sum(nota2):
        return sum(nota1) > sum(nota2)
    
    # Comparação de ano que cursou  
    elif semestre1[0] != semestre2[0]:
        return semestre1[0] > semestre2[0]
    
    # Comparação do semestre que cursou 
    elif semestre1[1] != semestre2[1]:
        return semestre1[1] > semestre2[1]
    
    # Comparação do nome
    elif nome1 != nome2:
        return nome1 < nome2
    
    # Comparação da Matrícula
    else:
        return m1 < m2

# Usando o merge e a funcao auxiliar para fazer as divisões e ordenar a lista de matriculas
def ordena(lista, dic):
    if len(lista) > 1:
        tam = len(lista)//2
        direita = lista[:tam]
        esquerda = lista[tam:]

        ordena(direita, dic)
        ordena(esquerda, dic)
    
        var1 = var2 = var3 = 0
        
        while len(esquerda) > var2 and var1 < len(direita):    
            if mergesort(direita[var1], esquerda[var2], dic):
                lista[var3] = direita[var1]
                var1 += 1
            else:
                lista[var3] = esquerda[var2]
                var2 += 1
            var3 += 1
        
        while var2 < len(esquerda):
            lista[var3] = esquerda[var2]
            var3 += 1
            var2 += 1
        
        while var1 < len(direita):
            lista[var3] = direita[var1]
            var3 += 1
            var1 += 1

    
#---------------#
# Busca Binária #
#---------------#
def pesquisa_binaria(lista, dic, item):
    ini, fim = 0, len(lista) - 1
   
    while ini <= fim:
        
        # Achando os indices da lista e as chaves do dicionario
        meio = (ini + fim) // 2
        mat = lista[meio]
        mat2 = lista[meio+1]
        _, _, nota_tupla, falta = dic[mat]
        _, _, nota1_tupla, falta1 = dic[mat2]
        
        # Somando as notas sem os extras
        nota = sum(nota_tupla)
        nota1 = sum(nota1_tupla)
       
       
        # Adicionando os extras
        if falta == 0 and nota <= 98:
            nota += 2
        elif falta == 0 and nota == 99:
            nota += 1
            
        if falta1 == 0 and nota1 <= 98:
            nota1 += 2
        elif falta1 == 0 and nota1 == 99:
            nota1 += 1        
        
        # Efitivando a busca
        if nota == item and  nota1 < 60:
            return meio
        elif nota >= item:
            ini = meio + 1
        else:
            fim = meio - 1
    return -1


#----------------------#
# Transformação em Txt #
#----------------------#
def escreverArquivo(matricula_alunos, dic): 
    # Usando a lista de Matriculas ordenada para fazer o txt ordenado
    with open('saida.txt' , 'w', encoding=' utf8') as f:
    
    # For para definirmos o ponto extra para cada nota    
        for i in matricula_alunos:
            nome, _, nota, falta = dic[i]
            nota = sum(nota)
            if falta == 0 and nota <= 98:
                f.write(nome+' - '+str(nota)+' +2')
            elif falta == 0 and nota == 99:
                f.write(nome+' - '+str(nota)+' +1')
            else:
                f.write(nome+' - '+str(nota))
            f.write('\n')
            

                        
#--------------------------------------------------------#
# Função Main: Transformação do Bin e Funções auxiliares #
#--------------------------------------------------------#
def main():
    
    # Abrindo o Arquivo.bin e transformando em Dicionário
    with open ('entrada.bin', 'rb') as u:
        dic = pickle.load(u)
    
    # Criando lista de matriculas
    matricula_alunos = list(dic.keys())
    
    # Função para odenação da lista
    ordena(matricula_alunos, dic)
    
    # Escrevendo arquivo txt
    escreverArquivo(matricula_alunos, dic)
    
    # Imprimindo o resultado da Busca binária de aprovados   
    print(pesquisa_binaria(matricula_alunos, dic, 60) +1)


if __name__ == '__main__':
    main()