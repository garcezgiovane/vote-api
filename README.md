# vote-api

### Projeto:
O projeto está configurado para rodar com um banco de dados em memória H2, após executá-lo se faz necessário fazer um Basic Auth para poder votar e rodar e executar os demais Endpoints. 

### Objetivo:
* * *

Minizar o tempo gasto para equipes decidirem o local do almoço. Essa solução promove as seguintes funcionalidades:

* Votar no restaurante favorito;
```
http://localhost:8080/api/v1/vote/{restaurantId}
```

* Encerrar a votação, assim marcando o restaurante mais votado como o escolhido do dia;
```
http://localhost:8080/api/v1/close
```

* Exibir o resultado da votação, após ser encerrada;
```
http://localhost:8080/api/v1/results
```
