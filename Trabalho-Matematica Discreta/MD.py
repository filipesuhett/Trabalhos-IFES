
#$ Bibliotecas usadas
import os
import binarytree as bt
from random import randint

#$ Funcao para encontrar um elemento alvo na arvore
def searchTree(root,target, initialRoot):
    if root != None:

        #$ Checando se o elemento alvo e igual ao no
        if root.values[0] == target:

            targetNode = root.values[0];
            valuesList = initialRoot.values;

            while None in valuesList:
                index = valuesList.index(None);
                valuesList.pop(index);

            valuesList.sort()
            targetIndex = valuesList.index(targetNode);
            
            print(f'Indice do elemento (In-Order): {targetIndex + 1}');

        #$ Chamando a funcao recursivamente para as sub-arvores
        else:
            if root.left != None:
                searchTree(root.left, target, initialRoot);

            if root.right != None:
                searchTree(root.right, target, initialRoot);


#$ Função para Limpar o console
def clear():
    if os.name == 'nt':
        os.system('cls')
    else:
        os.system('clear')


#$ Funcao principal
def main():

    clear();

    numbers = ['0','1','2','3','4','5','6','7','8','9'];

    dataType = None
    while dataType != True and dataType != False:
        print('''
=================================
||                             ||
||   1 -> Árvore com números   ||
||   2 -> Árvore com letras    ||
||                             ||
=================================

''')
        dataType = int(input('Escolha uma opção: '))
        if dataType == 1 or dataType == 2:
            if dataType == 1:
                dataType = False
            elif dataType == 2:
                dataType = True
        else:
            print('ERRO. Opção inválida.')

    listTF = [False, True];

    #$ Geração de árvores aleatórias com números inteiros
    root = bt.bst(randint(1,5), listTF[randint(0,1)], dataType);

    #$ Geração de ávores aleatórias com letras
    if dataType:
        while type(root.values[0]) != str:
            root = bt.bst(randint(1,5), listTF[randint(0,1)], dataType);

    print(root);


    target = input('Insira o elemento alvo: ');

    if dataType:
        while target in numbers:
            print('ERRO. Alvo inválido, insira uma letra.')
            target = input('Insira o elemento alvo: ');
        target = target.upper();
    else:
        while target[0] not in numbers:
            print('ERRO. Alvo inválido, insira um número.')
            target = input('Insira o elemento alvo: ');
        target = int(target);

    #$ Verifica se a árvore é completa
    if root.is_complete: treeIsComplete = "SIM";
    else: treeIsComplete = "NAO";

    #$ Verifica se a árvore é cheia
    if root.is_perfect: treeIsPerfect = "SIM";
    else: treeIsPerfect = "NAO";


    print(f'Árvore completa: {treeIsComplete}');
    print(f'Árvore cheia: {treeIsPerfect}');
    print(f'Altura da árvore: {root.height}');

    if target not in list(root.values):
        print('Elemento existente na árvore: NÃO');
    else:
        print('Elemento existente na árvore: SIM');
        searchTree(root, target, root);

if __name__ == '__main__':
    main();