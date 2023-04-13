#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void liberar_matriz(float **matriz, int altura) {
    for (int i = 0; i < altura; i++) {
        free(matriz[i]);
    }
    matriz = realloc(matriz, 0);
}

float** alocar(int altura, int largura) {
  float **image = (float **)malloc(altura * sizeof(float *));
  for (int i = 0; i < altura; i++) {
    image[i] = (float *)malloc(largura * sizeof(float));
  }
  return image;
}


void ler_cabecalho(FILE *pgm_file, char *tipo_pgm, int *largura, int *altura, unsigned int *max_val) {
  fscanf(pgm_file, "%2s %d %d %u", tipo_pgm, largura, altura, max_val);
}


void ler_pixels(FILE *pgm_file, int altura, int largura, float **image) {
  for (int i = 0; i < altura; i++) {
    for (int j = 0; j < largura; j++) {
      int pixel_value;
      fscanf(pgm_file, "%d", &pixel_value);
      image[i][j] = (float)pixel_value;
    }
  }
}


void aplicar_filtro_espelhamento(int altura, int largura, float **image) {
  unsigned int temp;
  int cont;

  for (int i = 0; i < altura; i++) {
    cont = -1;
    for (int j = 0; j < (largura / 2); j++) {
      temp = image[i][j];
      image[i][j] = image[i][largura + cont];
      image[i][largura + cont] = temp;
      cont--;
    }
  }
}


void aplicar_filtro_negativo(int altura, int largura, unsigned int max_val,
                             float **image) {
  for (int i = 0; i < altura; i++) {
    for (int j = 0; j < largura; j++) {
      image[i][j] = (float)max_val - image[i][j];
    }
  }
}


float media_vizinhos(int altura, int largura, float **image, int i, int j) {
  unsigned int media = image[i][j], cont = 1;

  for (int x = -1; x <= 1; x++) {
    for (int y = -1; y <= 1; y++) {
      if ((i + x >= 0) && (i + x < altura) && (j + y >= 0) && (j + y < largura)) {
        media += image[i + x][j + y];
        cont++;
      }
    }
  }
  media = trunc(media / cont);
  return media;
}


void aplicar_filtro_borramento(int altura, int largura, float **image) {
 float** aux = alocar(altura, largura);
  
  for (int i = 0; i < altura; i++) {
    for (int j = 0; j < largura; j++) {
      aux[i][j] = media_vizinhos(altura, largura, image, i, j);
    }
  }

  for (int i = 0; i < altura; i++) {
    memcpy(image[i], aux[i], largura * sizeof(float)); 
    }

  liberar_matriz(aux, altura);
}


float vizinho_maximo(int altura, int largura, float **image, int i, int j) {
  unsigned int max = image[i][j];

  for (int x = -1; x <= 1; x++) {
    for (int y = -1; y <= 1; y++) {
      if ((i + x >= 0) && (i + x < altura) && (j + y >= 0) && (j + y < largura)) {
        if (image[i + x][j + y] > max) {
          max = image[i + x][j + y];
        }
      }
    }
  }
  return max;
}


void aplicar_filtro_clareamento(int altura, int largura, float **image) {
 float** aux = alocar(altura, largura);
  for (int i = 0; i < altura; i++) {
    for (int j = 0; j < largura; j++) {
      aux[i][j] = vizinho_maximo(altura, largura, image, i, j);
    }
  }

  for (int i = 0; i < altura; i++) {
    memcpy(image[i], aux[i], largura * sizeof(float)); }

  liberar_matriz(aux, altura);
}


void escrever_cabecalho(FILE *pgm_file, char *tipo_pgm, int largura,
                        int altura, unsigned int max_val) {
  fprintf(pgm_file, "%s\n%d %d\n%u\n", tipo_pgm, largura, altura, max_val);
}


void escrever_pixels(FILE *pgm_file, int altura, int largura, float **image) {
  for (int i = 0; i < altura; i++) {
    for (int j = 0; j < largura; j++) {
      fprintf(pgm_file, "%d ", (int)image[i][j]);
    }
    fprintf(pgm_file, "\n");
  }
}


int verificar_erro (FILE *file){
  if (file == NULL) {
    fprintf(stderr, "\n\nErro ao abrir o arquivo\n\n");
    return 1;
    }
  return 0;
}


void imprime_menu(void) {
  
    printf("===== MENU =====\n");
    printf("1. Selecionar imagem\n");
    printf("2. Aplicar filtro\n");
    printf("3. Salvar imagem\n");
    printf("4. Sair\n");
    printf("================\n");
    printf("Escolha uma opção: ");
    fflush(stdin); // limpa o buffer de entrada
}


int main() {
  char tipo_pgm[3], caminho_salvar[100], caminhoImagem[100], filename[100], path[100], path1[100];
  int largura, altura;
  unsigned int max_val;
  char opcao; 
  int imagemEscolhida = 0;
  int filtroAplicado = 0;
  int escolha_filtro = 0;
  float** image;

  while (opcao != '4') { // laço infinito para manter o menu em execução
    
    // exibe o menu na tela
    imprime_menu();
    scanf("%c", &opcao);

    
    switch (opcao) {
    case '1':
      printf("Qual o nome do seu arquivo sem o .pgm? ");
      scanf("%99s", filename); // limita a quantidade de caracteres lidos e corrige
                           // o formato de leitura
      
    // solicita o caminho da imagem ao usuário
      printf("Informe o caminho global até a pasta onde está a imagem a ser processada ou pressione "
             "1 para usar o caminho padrão:\n");
      scanf("%99s", caminhoImagem); // limita a quantidade de caracteres lidos
                                    // corrige o formato de leitura

      getchar();
      
      // Abre o arquivo PGM
      if (caminhoImagem[0] == '1') {
        sprintf(path, "input/%s.pgm", filename);
      } 
      else {
        sprintf(path, "%s/%s.pgm", caminhoImagem, filename);
      }

      FILE *pgm_file = fopen(path, "rb");
      if (verificar_erro(pgm_file) == 1)
        break;

      // Lê o cabeçalho do arquivo PGM
      ler_cabecalho(pgm_file, tipo_pgm, &largura, &altura, &max_val);

      // Cria uma matriz para armazenar a imagem
      image = alocar(altura, largura);

      // Lê os pixels do arquivo PGM e armazena na matriz
      ler_pixels(pgm_file, altura, largura, image);

      // Fecha o arquivo de entrada
      fclose(pgm_file);
      printf("\n\nCarregado com Sucesso\n\n");
      break;

    case '2': // aplicar filtro
      
    if (caminhoImagem[0] == '\0') {
        printf("Selecione primeiro a imagem no menu (opcao 1)\n");
        getchar();
        break;
    }

      // Escolha do filtro
      printf("\nEscolha um filtro:\n");
      printf("1. Filtro Negativo\n");
      printf("2. Filtro de Espelhamento\n");
      printf("3. Filtro de Borramento\n");
      printf("4. Filtro de Clareação\n");


      scanf("%d", &escolha_filtro);

      getchar();

      switch (escolha_filtro) {
      case 1: 
        aplicar_filtro_negativo(altura, largura, max_val, image);
        printf("\nFiltro Negativo aplicado com sucesso!\n");
        break;

      case 2: 
        aplicar_filtro_espelhamento(altura, largura, image);
        printf("\nFiltro de Espelhamento aplicado com sucesso!\n");
        break;

      case 3: 
        aplicar_filtro_borramento(altura, largura, image);
        printf("\nFiltro de Borramento aplicado com sucesso!\n");
        break;

      case 4: 
        aplicar_filtro_clareamento(altura, largura, image);
        printf("\nFiltro de Clareação aplicado com sucesso!\n");
        break;

      default:
        printf("\nOpcao invalida!\n\n");
        break;
      }
      break;

    case '3':// Salvar a imagem resultante

      if (escolha_filtro == 0) {
        printf("\n\nPor favor siga a ordem de ações indicada no menu\n\n");
        getchar();
        break;
      }
      
      printf("\nDeseja salvar a imagem resultante?\n");
      printf("1. Sim\n");
      printf("2. Nao\n");

      int escolha_salvar;
      scanf("%d", &escolha_salvar);

      if (escolha_salvar == 1) {
        printf("\nInforme o caminho global até a pasta onde quer salvar a imagem. Caso seja padrão "
               "digite 1:\n");
        scanf("%s", caminho_salvar);

        getchar();

        // Gravar a imagem resultante
        if (caminho_salvar[0] == '1') {
          sprintf(path1, "output/%s_%d.pgm", filename, escolha_filtro);
        } 
        else {
          sprintf(path1, "%s/%s_%d.pgm", caminho_salvar, filename,  escolha_filtro);
        }

        
        FILE *pgm_file = fopen(path1, "wb");
        verificar_erro(pgm_file);

        escrever_cabecalho(pgm_file, tipo_pgm, largura, altura, max_val);

        escrever_pixels(pgm_file, altura, largura, image);

        // Fecha o arquivo de saída
        fclose(pgm_file);
        printf("Imagem salva com sucesso!\n");
      }
      break;
    
    default:
      if (opcao == '4') {
        break;
      }
      else {
        printf("\n\n\nOpcao invalida.\n\n\n");
        getchar();
        fflush(stdin);
        break;
      }  
    }
  }
  liberar_matriz(image, altura);
  return 0;
}