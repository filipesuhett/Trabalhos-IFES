def verificar_pertinencia_fibonacci(n):
  ult=0
  pen=1
  aux = True
  
  if (n==1 or n==0):
      return 'Pertence a sequencia de fibonacci'
  else:
    while aux == True:
        ter = ult + pen
        pen = ult
        ult = ter
        if ult == n:
            return 'Pertence a sequencia de fibonacci'
        elif ult > n:
            return 'Não pertence a sequencia de fibonacci'

    
def main():
  n = int(input('Qual termo quer verificar se pertence a sequencia de fibonacci? '))
  print(verificar_pertinencia_fibonacci(n))

if __name__=="__main__":
  main()