# 📋 Instruções de Instalação e Configuração

## ☕ Instalação do Java JDK

Para compilar e executar este projeto, você precisa do **JDK (Java Development Kit)**, não apenas o JRE.

### 🔍 Verificar se JDK está instalado

Abra o **Prompt de Comando** ou **PowerShell** e digite:

```bash
java -version
javac -version
```

- Se apenas `java -version` funcionar: Você tem apenas o JRE
- Se ambos funcionarem: JDK está instalado corretamente

### 💾 Download do JDK

1. **Oracle JDK** (recomendado para este projeto):
   - Acesse: https://www.oracle.com/java/technologies/downloads/
   - Baixe o **JDK 8** ou superior (JDK 11 LTS recomendado)
   - Instale seguindo as instruções padrão

2. **OpenJDK** (alternativa gratuita):
   - Acesse: https://adoptium.net/
   - Baixe o **Eclipse Temurin** (ex-AdoptOpenJDK)

### ⚙️ Configuração de Variáveis de Ambiente (Windows)

1. **Localize a instalação do JDK**:
   - Normalmente em: `C:\Program Files\Java\jdk-11.x.x\`

2. **Configure JAVA_HOME**:
   - Painel de Controle → Sistema → Configurações Avançadas
   - Variáveis de Ambiente
   - Nova variável de sistema:
     - Nome: `JAVA_HOME`
     - Valor: caminho do JDK (ex: `C:\Program Files\Java\jdk-11.0.x`)

3. **Configure o PATH**:
   - Edite a variável PATH do sistema
   - Adicione: `%JAVA_HOME%\bin`

### ✅ Verificação Final

Reinicie o terminal e teste:
```bash
java -version
javac -version
```

Ambos devem funcionar e mostrar a mesma versão.

## 🚀 Executando o Projeto

### 📁 Via Arquivos Batch (Mais Fácil)

1. **Compilar**: Execute `compilar.bat`
2. **Executar**: Execute `executar.bat`

### 💻 Via Linha de Comando

```bash
# Navegar para a pasta do projeto
cd "I:\etc\Projetos do Marcelinho\CacadoresSaciCRUD"

# Compilar
javac -cp . src/main/Main.java src/model/*.java src/dao/*.java src/service/*.java src/view/*.java

# Executar
java -cp src main.Main
```

### 🔧 Via IDE

1. **Visual Studio Code**:
   - Instale a extensão "Extension Pack for Java"
   - Abra a pasta do projeto
   - Execute `Main.java` com F5

2. **IntelliJ IDEA**:
   - Open → Selecione a pasta do projeto
   - Configure o SDK do Java
   - Execute Main.java

3. **Eclipse**:
   - New → Java Project from Existing Source
   - Importe a pasta src
   - Execute Main.java

## 🐛 Solução de Problemas

### ❌ Erro: "javac não é reconhecido"
- **Problema**: JDK não instalado ou PATH não configurado
- **Solução**: Instale o JDK e configure as variáveis de ambiente

### ❌ Erro: "Could not find or load main class"
- **Problema**: Classpath incorreto
- **Solução**: Execute de dentro da pasta do projeto com `-cp src`

### ❌ Erro de compilação nos enums
- **Problema**: Java muito antigo
- **Solução**: Use Java 8 ou superior

### ❌ Caracteres especiais não aparecem
- **Problema**: Encoding do terminal
- **Solução**: Configure o terminal para UTF-8 ou execute via IDE

## 📚 Para Professores

### 🎯 Configuração em Laboratório

1. **Verificar JDK** em todas as máquinas
2. **Criar atalho** para os arquivos .bat
3. **Testar execução** antes da aula
4. **Preparar slides** explicando a estrutura do código

### 📊 Métricas do Projeto

- **Linhas de código**: ~1000 linhas
- **Classes**: 7 principais
- **Conceitos cobertos**: OOP, CRUD, Validação, Interface
- **Tempo de aula estimado**: 2-3 horas para explicação completa

### 🎓 Exercícios Sugeridos

1. **Iniciante**: Adicionar novos Sacis pré-definidos
2. **Intermediário**: Criar nova funcionalidade de busca
3. **Avançado**: Implementar persistência em arquivo
4. **Desafio**: Criar interface gráfica simples

---

💡 **Dica**: Mantenha este arquivo sempre atualizado conforme o projeto evolui!
