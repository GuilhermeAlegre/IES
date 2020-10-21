# IES-WeatherRadar 

**Carlos Guilherme Dias Nunes Alegre, nº mec: 64970** 

###Maven
##### Archetype
 Defindo como um padrão original ou um modelo que as coisas do mesmo tipo são feitas.
No maven um archtype é um template de projecto que juntamente de algumas configuraçẽs 
feitas pelo utilizador, gera um projecto de trabalho maven que é adaptado aos 
requisitos seus requisitos. O uso de archtypes promove o desenvolvimento de um projecto de
forma rápida e usando boas práticas.

##### GroupId
Identifica unicamente o projecto no meio de todos os outro projectos. Por esse devemos seguir 
o Java's package name.O objectivo principal do convenção do package naming é manter um 
package como único sem evitando as colisões
- Não usar o nome java ou javax for os packages. 
- Apelidar o package com lower case, prevenindo conflitos os nomes das classes e interfaces. 
- O nome do package deve estar invertido de acordo com o nome do dominio de internet.
 
##### ArtifactId
É o nome do jar sem versão. Pode ser escolhido qualquer nome com lower case e sem caracteres estranhos.


#### O que é maven goal? quais os principais "maven goals" e a respetiva sequência de invocação?
Maven goal é uma única tarefa que faz um determindado trabalho.
O maven tem várias fases de construção.A cada fases e uma sequência de goals, e cada goal é responsavel 
por uma determinada tarefa.
Quando corremos uma fase, todos os goals são ligados a essa fase e são executados em ordem.

    - validate: check if all information necessary for the build is available
    - compile: compile the source code
    - test-compile: compile the test source code
    - test: run unit tests
    - package: package compiled source code into the distributable format (jar, war, …)
    - integration-test: process and deploy the package if needed to run integration tests
    - install: install the package to a local repository
    - deploy: copy the package to the remote repository

ref link - https://www.baeldung.com/maven-goals-phases

#### Docker cheat sheet
Para este efeito resolvi usar uma data cheat sheet de terceiros. Encontra-se na raiz (nome: DockerCheatSheet)
E o próprio site do docker que contem uma documentação muito completa.

#### Docker container ls --all

    CONTAINER ID        IMAGE                 COMMAND                  CREATED             STATUS                      PORTS                                            NAMES
    bfe376ddb3a3        postgres:latest       "docker-entrypoint.s…"   31 minutes ago      Up 31 minutes               5444/tcp                                         postgres_sql
    af961591fa5f        portainer/portainer   "/portainer"             20 minutes ago      Up 20 minutes               0.0.0.0:8000->8000/tcp, 0.0.0.0:9000->9000/tcp   condescending_ardinghelli
    10244042ed73        portainer/portainer   "/portainer"             10 hours ago        Exited (2) 4 hours ago                                                      hardcore_wozniak
    fe74fc26e016        friendlyhello         "python app.py"          11 hours ago        Exited (137) 7 hours ago                                                    sad_euler

Também pode ser encontrado o resultado do log na pasta lab1.3 ficheiro- docker_container_ls_all

#### Relevância de configurar "volumes" quando se prepara um countainer para servir uma base de dados

Os volumes servem para armazenar dados  e fazem-no fora do default Union File System. São guardados como ficheiros e 
directórios normais no host filesystem. Eles garantem que todos os dados guardados na base de dados de um container
não sejam perdidos quando ocorre um reboot, ou seja, não deixam a base se dados ser volátil (persistencia dos dados).
 Ainda permitem que os containers partilhem dados através dos volumes.
      


