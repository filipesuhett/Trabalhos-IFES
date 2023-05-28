#---------------------------------#
# CÓDIGO IC - ECGS                #
# ALUNO: FILIPE SUHETT            #
# PROFESSOR: Gabriel Tozatto Zago #
#---------------------------------#

#-------------------#
# Imports do Código #
#-------------------#

from IPython.display import display
import matplotlib.pyplot as plt
import numpy as np
import os
import shutil
import posixpath
import pandas as pd

import wfdb

#---------------------------------#
# Plotar trechos de todos os ECGS #
#---------------------------------#

def op1(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments):
  cont = 100
  aux = 0
  amount = 3
  
  # Loop para acessar todos os ECGS 
  while cont < 234:  
    try:    
      
      # Lendo o arquivo atr do ECG
      record1 = wfdb.rdann('Data/'+str(cont), 'atr')
      
      # Transformando os dados ECG em Dicionário e Tupla
      rec1 = record1.__dict__
      sample = tuple(rec1['sample'])
      
      # Definindo o Trecho do ECG e o Transformando em Dicinário
      record = wfdb.rdrecord('Data/'+str(cont), sampfrom=sample[2]-40 , sampto=sample[4]+40)
      rec = record.__dict__
      
      # Plotando o Grafico com os Sinais
      wfdb.plot_wfdb(record=record, title='ECG | '+str(cont)+' |', ecg_grids='all', plot_sym=True)
      
      # Chamando a função do DataFrame Pandas para salvar os dados do ECG
      datframe(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments,rec,rec1,amount)
      cont += 1
      print()
    except:
      cont += 1
      
#-------------------------------------------------------#
# Plotar tipos diferentes de sinais presentes nos ECGs  #
#-------------------------------------------------------#

#-----------------------------------------------------------#
# OBS:                                                      #
# 1. Sempre Plotamos 5 Sinais                               #
# 2. Sinal demonstrado na maioria das vezes será o 3º Sinal #
# 3. Caso não seja o 3º Sinal, será o 1º ou 2º              #
#-----------------------------------------------------------#

def opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments):
  cont = 100
  aux = 0
  amount = 5
  
  # Loop para acessar todos os ECGS 
  while cont < 234 and aux != 3:
    try:
      
      # Lendo o arquivo atr do ECG
      record1 = wfdb.rdann('Data/'+str(cont), 'atr')
      
      # Transformando os dados ECG em Dicionário e Tuplas
      rec1 = record1.__dict__
      symbol = tuple(rec1['symbol'])
      sample = tuple(rec1['sample'])
      
      # Condição para verificar se existe aquele batimento nesse ECG
      if sym in symbol:
        
        # Condição para casos que o Batimento é o primeiro do ECG
        if symbol.index(sym) > 1:
          
          # Definindo de qual sinal irá começar o grafico
          index = symbol.index(sym)-2
          
          # Definindo o Trecho do ECG e o Transformando em Dicinário
          record = wfdb.rdrecord('Data/'+str(cont), sampfrom=sample[index]-20 , sampto=sample[index+4]+20)
          rec = record.__dict__
          
          # Plotando o Grafico com os Sinais
          wfdb.plot_wfdb(record=record, title=name+' | ECG-'+str(cont), ecg_grids='all', plot_sym=True)
          
          # Chamando a função do DataFrame Pandas para salvar os dados do ECG
          datframe(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments,rec,rec1,amount)
          aux += 1
          print()
        
        # Condição para os outros casos
        else:
          
          # Condição para casos que o Batimento é o primeiro do ECG
          index = symbol.index(sym)
          
          # Definindo o Trecho do ECG e o Transformando em Dicinário
          record = wfdb.rdrecord('Data/'+str(cont), sampfrom=sample[index]-20 , sampto=sample[index+4]+20)
          rec = record.__dict__
          
          # Plotando o Grafico com os Sinais
          wfdb.plot_wfdb(record=record, title=name+' | ECG-'+str(cont), ecg_grids='all', plot_sym=True)
          
          # Chamando a função do DataFrame Pandas para salvar os dados do ECG
          datframe(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments,rec,rec1,amount)
          aux += 1
          print()
      cont += 1
    except:
      cont += 1    
      
#------------------#
# Dataframe Pandas #
#------------------#

def datframe(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments,rec,rec1,amount):     
  
  # Definindo o tempo que se passou em cada sinal plotado
  time = ((rec['sig_len'])/10)*0.025
  
  # Definindo o tamanho para no maximo 3 casas depois da vrigula
  time = round(time,3)

  #---------#
  # Appends #
  #---------#
  
  # Nome do ECG
  namee.append('ECG-'+str(rec['record_name']))

  # Tamanho do Grafico de sinais
  signal_length.append(int(rec['sig_len']))
  
  # Quantidade de Sinais
  amount_of_signs.append(amount)
  
  # Tempo que passou com os sinais passados
  time_elapsed.append(str(time)+' sec')
  
  # Comentarios importantes
  coments.append(name)        
  
#------------------------------------#
# Limpa Tela - Não funciona no Colab #
#------------------------------------#

def limpatela():
    if os.name == 'nt':
        os.system('cls')
    else:
        os.system('clear')


#---------------------------------------------#
# Main contendo as opções para serem plotadas #
#---------------------------------------------#

def main():
  
  # Menu que será exibido
  menu = '''---------------------------------------------
    
              Menu
              1) Plot snippets of all ECGS
              2) Normal beat
              3) Left bundle branch block beat
              4) Right bundle branch block beat
              5) Aberrated atrial premature beat
              6) Premature ventricular contraction
              7) Fusion of ventricular and normal beat
              8) Nodal (junctional) premature beat
              9) Atrial premature contraction
              10) Premature or ectopic supraventricular beat
              11) Ventricular escape beat
              12) Nodal (junctional) escape beat
              13) Paced beat
              14) Unclassifiable beat
              15) Exit

              
              Which operation do you want? '''
  
  # Definindo a Operação
  op = int(input(menu))
  
  # Definindo as listas e dicionarios para o dataframe
  data = {}
  namee = []
  signal_length = []
  amount_of_signs = []
  time_elapsed = []
  coments = []


  #------------------------------#
  # Indice                       #
  # 1. name = Nome da Operação   #
  # 2. sym = Simbolo da operação #
  #------------------------------#
  
  # Loop para para definir as operações
  while op != 15:
      limpatela()
      if op == 1:
        name = 'Plot of all ECGS'
        
        # Chamando a função de Plot
        op1(name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)
      elif op == 2:
        sym = 'N'
        name = 'Normal beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)
      elif op == 3:
        sym = 'L'
        name = 'Left bundle branch block beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)
      elif op == 4:
        sym = 'R'
        name = 'Right bundle branch block beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)
      elif op == 5:
        sym = 'a'
        name = 'Aberrated atrial premature beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)
      elif op == 6:
        sym = 'V'
        name = 'Premature ventricular contraction'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)        
      elif op == 7:
        sym = 'F'
        name = 'Fusion of ventricular and normal beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)        
      elif op == 8:
        sym = 'J'
        name = 'Nodal (junctional) premature beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)        
      elif op == 9:
        sym = 'A'
        name = 'Atrial premature contraction'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)       
      elif op == 10:
        sym = 'S'
        name = 'Premature or ectopic supraventricular beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)          
      elif op == 11:
        sym = 'E'
        name = 'Ventricular escape beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)          
      elif op == 12:
        sym = 'j'
        name = 'Nodal (junctional) escape beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)          
      elif op == 13:
        sym = '/'
        name = 'Paced beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)          
      elif op == 14:
        sym = 'Q'
        name = 'Unclassifiable beat'
        
        # Chamando a função de Plot
        opprin(sym,name,data,namee,signal_length,amount_of_signs,time_elapsed,coments)                                              
      else:
          print('Invalid Operation. Try again')
      op = int(input(menu))
  
  
  # Transformando as listas em um dicionário
  data["name"] = namee
  data["signal_length"] = signal_length
  data["amount_of_signals"] = amount_of_signs
  data['time_elapsed'] = time_elapsed
  data['comments'] = coments

  # Criando e Printando o Dataframe
  df = pd.DataFrame(data)
  display(df)

  df.to_csv("ECGs.csv", sep ='\t', encoding = 'utf-8')


main()    