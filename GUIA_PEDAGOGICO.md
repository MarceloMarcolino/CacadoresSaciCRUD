# 🎓 Guia Pedagógico - Caçadores de Saci CRUD

Este guia foi criado para ajudar professores a utilizar este projeto como material didático em aulas de programação.

## 📋 Plano de Aula Sugerido

### 🏁 Aula 1: Introdução e Conceitos (45 min)

#### ⏰ Tempo: 10 minutos - Apresentação
- Apresentar o jogo "Caçadores de Saci"
- Explicar o contexto do folclore brasileiro
- Mostrar os elementos do jogo (Sacis, tipos, atributos)

#### ⏰ Tempo: 15 minutos - Conceitos CRUD
- **C**reate - Criar novos registros
- **R**ead - Consultar/ler registros
- **U**pdate - Atualizar registros existentes
- **D**elete - Excluir registros

#### ⏰ Tempo: 20 minutos - Demonstração Prática
- Executar o sistema
- Mostrar cada operação CRUD
- Destacar as validações e mensagens de erro
- Mostrar as estatísticas e relatórios

### 🏗️ Aula 2: Arquitetura e Padrões (90 min)

#### ⏰ Tempo: 20 minutos - Estrutura de Pastas
```
src/
├── main/     - Ponto de entrada
├── model/    - Representação dos dados
├── dao/      - Acesso aos dados
├── service/  - Lógica de negócio
└── view/     - Interface com usuário
```

#### ⏰ Tempo: 25 minutos - Classes Modelo
- **Saci.java** - Classe principal
- **Enums** - TipoSaci, Temperamento, Rank
- **Encapsulamento** - getters/setters
- **toString()** e **equals()**

#### ⏰ Tempo: 25 minutos - Padrão DAO
- **SaciDAO.java** - Separação de responsabilidades
- **Lista em memória** vs banco de dados
- **Métodos CRUD** básicos
- **Tratamento de erros**

#### ⏰ Tempo: 20 minutos - Service Layer
- **SaciService.java** - Lógica de negócio
- **Validações** de entrada
- **Regras de negócio**
- **Intermediação** entre View e DAO

### 🖥️ Aula 3: Interface e Experiência do Usuário (45 min)

#### ⏰ Tempo: 15 minutos - Interface Console
- **SaciView.java** - Interação com usuário
- **Scanner** para entrada
- **Menu** estruturado
- **Formatação** de saída

#### ⏰ Tempo: 15 minutos - UX em Console
- **Emojis** para melhor visualização
- **Mensagens** claras e amigáveis
- **Validação** de entrada
- **Feedback** ao usuário

#### ⏰ Tempo: 15 minutos - Exercícios Práticos
- Modificar mensagens
- Adicionar novas opções de menu
- Melhorar formatação

## 🎯 Objetivos de Aprendizagem

### 🟢 Conceitos Básicos (Iniciante)
- [ ] Entender o que é CRUD
- [ ] Conhecer a estrutura de classes Java
- [ ] Compreender getters e setters
- [ ] Saber usar enums
- [ ] Executar o programa

### 🟡 Conceitos Intermediários
- [ ] Compreender padrões DAO e Service
- [ ] Implementar validações básicas
- [ ] Modificar funcionalidades existentes
- [ ] Tratar exceções
- [ ] Usar Collections (ArrayList)

### 🔴 Conceitos Avançados
- [ ] Implementar novas funcionalidades
- [ ] Refatorar código existente
- [ ] Criar testes unitários
- [ ] Implementar persistência em arquivo
- [ ] Otimizar performance

## 🛠️ Exercícios Práticos

### 🎯 Exercício 1: Modificação Básica (Fácil)
**Objetivo**: Adicionar um novo tipo de Saci

**Passos**:
1. Edite `TipoSaci.java`
2. Adicione: `MISTICO("Místico", "Saci com poderes especiais")`
3. Compile e teste
4. Crie um Saci do novo tipo

**Tempo estimado**: 15 minutos

### 🎯 Exercício 2: Nova Funcionalidade (Médio)
**Objetivo**: Implementar busca por faixa de poder

**Passos**:
1. Adicione método em `SaciDAO.java`
2. Adicione lógica em `SaciService.java`
3. Adicione opção no menu `SaciView.java`
4. Teste a funcionalidade

**Tempo estimado**: 45 minutos

### 🎯 Exercício 3: Validação Avançada (Difícil)
**Objetivo**: Validar atributos por tipo de Saci

**Regras**:
- Sacis Valentes: Potência mínima 15
- Sacis Cativantes: Magia mínima 15
- Sacis Rígidos: Resistência mínima 15

**Tempo estimado**: 60 minutos

### 🎯 Exercício 4: Persistência (Desafio)
**Objetivo**: Salvar dados em arquivo JSON

**Passos**:
1. Adicionar biblioteca JSON (Gson)
2. Criar métodos salvar/carregar
3. Modificar inicialização
4. Testar persistência

**Tempo estimado**: 90 minutos

## 📊 Critérios de Avaliação

### 🟢 Básico (60-70%)
- Executa o programa sem erros
- Consegue realizar operações CRUD básicas
- Entende a estrutura geral do código
- Completa exercícios simples

### 🟡 Intermediário (70-85%)
- Modifica código existente com sucesso
- Implementa validações básicas
- Compreende padrões utilizados
- Resolve problemas simples independentemente

### 🔴 Avançado (85-100%)
- Implementa novas funcionalidades complexas
- Refatora código mantendo funcionalidade
- Propõe melhorias arquiteturais
- Demonstra criatividade e pensamento crítico

## 🔧 Dicas para Professores

### ✅ Preparação da Aula
1. **Teste o ambiente** antes da aula
2. **Prepare exemplos** de Sacis do folclore
3. **Tenha backup** do código funcionando
4. **Prepare slides** com diagramas da arquitetura

### 🎬 Durante a Aula
1. **Comece com demonstração** prática
2. **Explique conceitos** durante a codificação
3. **Incentive perguntas** e discussão
4. **Faça refatorações** ao vivo

### 📝 Após a Aula
1. **Disponibilize o código** completo
2. **Liste exercícios** extras
3. **Crie quiz** sobre conceitos
4. **Prepare próxima aula** com base no feedback

### 💡 Adaptações Possíveis

#### Para Turmas Iniciantes:
- Foque mais na execução e uso
- Simplifique explicações de padrões
- Mais exercícios práticos básicos

#### Para Turmas Avançadas:
- Discuta padrões alternativos
- Proponha otimizações
- Exercícios de refatoração
- Comparação com frameworks

## 🌟 Extensões do Projeto

### 📱 Interface Gráfica
- Migrar para JavaFX
- Criar formulários visuais
- Adicionar imagens dos Sacis

### 🌐 Sistema Web
- Spring Boot + Thymeleaf
- API REST
- Frontend React/Angular

### 🗄️ Banco de Dados
- MySQL/PostgreSQL
- Hibernate/JPA
- Migrations

### 🧪 Testes
- JUnit 5
- Mockito
- Testes de integração

## 📚 Recursos Complementares

### 📖 Leitura Recomendada
- "Clean Code" - Robert Martin
- "Effective Java" - Joshua Bloch
- "Design Patterns" - Gang of Four

### 🔗 Links Úteis
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Spring Boot Guides](https://spring.io/guides)
- [Baeldung Java Articles](https://www.baeldung.com/)

### 🎥 Vídeos Complementares
- Conceitos de OOP em Java
- Padrões de projeto com exemplos
- Boas práticas de codificação

---

**🎯 Lembre-se**: O objetivo é ensinar conceitos através de um projeto prático e divertido que conecta programação com cultura brasileira!
