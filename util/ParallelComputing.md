# Computação paralela


## Porque precisamos de computação paralela

Programação paralela é muito mais complicada que programação sequencial
- Separar computações sequenciais em subcomputações paralelas pode ser desafiador e, algumas vezes, impossível
- Garantir a exatidão do programa é mais dificil, com vários tipos novos diferentes de erros

O principal beneficio para computação paralela é a velocidade de execução por conta das limitações de hardware... é o único motivo para pagar por essa complexidade

## Computação paralela versus computação concorrente

Não são a mesma coisa apesar de estar bem relacionadas

Computação paralela usa o hardware paralelo (cores de um processador ou diversos servidores) para executar um algoritmo de forma mais rápida. Eficiência é a preocupação principal

Computação concorrente pode ou não usar várias execuções ao mesmo tempo. Se precuopa com modularidade, responsividade e manutenabilidade

## Diversas formas de paralelismo

- Bit-level 
- Instruction-level  
- Task-level

## Classes de computadores paralelos

- Multi-core processors
- Symmetric multiprocessors
- Graphic processing unit
- Field-programmable gate arrays
- Computer clusters
  
# Paralelismo na JVM

Definição de processo
Dentro de processos, diversas threads

Threads compartilham a mesma memória dentro do mesmo Processo

Para iniciar uma thread addicional a main thread, temos:
1. Definir a Thread subclass
2. Instanciar a nova Thread (criar o objeto)
3. Inicializar chamando o Start dessa nova Thread

Atomicidade e Bloco sincronizado

Multiplos blocos sincronizados podem ser compostos

Memory model é uma quantidade de regras que descreve como threads fazem a interação com memória compartilhada

Regras básicas de memory model (JVM):
- Duas threads escrevendo em locais separados na memória não precisam de sincronização / bloco sincronizado
- A thread X que chama join em outra thread Y é garantia de esperar todas as escritas da thread Y depois do retorno do join

O paralelismo constitui basicamente na construção de duas novas estruturas:
- Threads
- Sincronização primitivas como blocos sincronizados

Na criação de uma função para executar algo em paralelo, tomar cuidado para sempre passar o parâmetro por referencia (: =>) e não por valor (:)... se passar por valor, a execução será feita antes da chamada da função e de nada adianta o paralelo

Paralelismo eficiente precisa de suporte de:
- Linguagens e bibliotecas
- Maquinas virtuais
- Sistema operação
- Hardware

Implementações de paralelismo usando a JVM fazem:
- Tipicamente mapeia para threads de sistema operacional
- O SO pode agendar diferentes threads em multiplos núcleos de processamento

Se tiver recursos suficientes, a execução em paralelo pode ser muito rápida

Não esquecer que, executando em um mesmo computador o paralelo é dificil ver a melhora de velocidade porque todos as threads executando estão concorrendo acesso a mesma memória física

## O quão rapido um programa em paralelo pode ser?

Complexidade ciclomática de execução em paralelo
- Depende dos recursos disponíveis
- Há duas mensurações para o programa:
  - Work W(e): numero de passos e quanto demoraria se não fosse paralelo
  - Depth D(e): numero de passos que limitamos o paralelismo

Calculando, temos: 
- $$ W(parallel(e1, e2)) = W(e1) + W(e2) + (constante de transição de tarefas) $$
- $$ D(parallel(e1, e2)) = max(D(e1), D(e2)) + (constante de junção) $$
- $$ Onde: D <= W $$

Podemos então dizer que o tempo de execução é:

$$
D(e) + (W(e)/P)
$$

onde P = quantidade de unidade de processamento paralelo disponíveis para essa execução

## Benchmarks

### Difereça entre benchmark e teste
- Testes servem para avaliar o comportamento do sistema, principalmente na questão de negócio e de funcionamento de componentes
- Benchmarks avaliam as métricas de funcionamento e performance de um sistema ou de parte dele
  
Exemplos de benchmarks:
- Running time
- Memory footprint
- Metric traffic
- Disk usage
- Latency

## Porquê avaliamos benchmark em algoritmos paralelos?

O principal beneficio de se colocar uma execução em paralela é justamente o ganho de performance

Por isso, a avaliação de benchmark se torna muito mais importante em algoritmos paralelos que nos sequenciais

Se a performance não te beneficia, é melhor criar algoritmos sequenciais pois não cria dificuldade sem nenhum benefício

Performance, em especial o tempo de execução (running time) é influenciado pelos seguintes pontos:
- Velocidade do processador
- Quantidade de processadores usados ou núcleos
- Latencia e taxa de transferencia da memória
- Cache do processador e seu comportamento
- Comportamento de execução (ex. Garbage Collector, JIT compilação, thread, agendamento de execução, etc)

*Book: What Every Programmer Should Know About Memory, by Ulrich Drepper*

Medir performance não é simples, normalmente a performance é uma variável randomica

Para medir a performance, temos que:
- Repetir a execução varias vezes e capturar o tempo de execução de cada uma delas
- Fazer tratamento estatístico, tirando a média e a variânça
- Eliminar outliers
- Assegurar estado inicial para que todas as execuções partam do mesmo presuposto
- Prevenir anomalias (ex: GC, JIT compilation, otimizações agressivas)

## ScalaMeter

ScalaMeter é um framework de benchmarking e performance em teste de regressão para a JVM

Para adicionar o ScalaMeter no projeto temos que incluir a seguinte dependencia:

~~~ scala
libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.6"
~~~ 

Depois, importa para a execução
~~~ scala
import org.scalameter._ 

val time = measure {
    (0 until 1000000).toArray
}

println(s"Array initialization time: $time ms")
~~~

### Outras opções para otimizar o ScalaMeter

- ``` Warmer ``` para executar o aquecimento, retirando os primeiros que podem não ser ideias para a média
- ``` Measurer.Default ``` retorna o tempo de execução plano
- ``` IgnoringGC ``` executa sem as pausas para o GC
- ``` OutlierElimination ``` remove os outliers estatisticos
- ``` MemoryFootprint ``` a pegada de memória de um objeto
- ``` GarbageCollectionCycles ``` numero total de pausas da GC
- Novas versões do ScalaMeter podem também contar a quantidade de vezes que um metodo é invocado e de pacotes

Mais em https://scalameter.github.io 

