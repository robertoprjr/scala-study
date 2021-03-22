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

## Atomicidade

Bloco sincronizado

Multiplos blocos sincronizados podem ser compostos


