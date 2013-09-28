CREATE DATABASE SGE

USE SGE

CREATE TABLE TB_ALUNO
  (
    NM_ALUNO        VARCHAR (120) NOT NULL ,
    SEXO_ALUNO      CHAR (1) NOT NULL DEFAULT 'F' ,
    RG_ALUNO        VARCHAR (11) NOT NULL ,
    CPF_ALUNO       VARCHAR (11) NOT NULL ,
    DT_NASC_ALUNO   VARCHAR (10) NOT NULL ,
    TEL_ALUNO       VARCHAR (10) NOT NULL ,
    CEL_ALUNO       VARCHAR (11) ,
    END_ALUNO       VARCHAR (120) NOT NULL ,
    EMAIL_ALUNO     VARCHAR (120) ,
    RM_ALUNO        INTEGER IDENTITY CONSTRAINT TB_ALUNO_PK PRIMARY KEY NOT NULL ,
    NM_TURMA        VARCHAR (120) NOT NULL ,
    NM_RESPONSAVEL  VARCHAR (120) ,    
CHECK
(
  SEXO_ALUNO IN ('F', 'M')
))
GO

ALTER TABLE TB_ALUNO
ADD
COD_TURMA INTEGER CONSTRAINT TB_ALUNO_FK FOREIGN KEY REFERENCES TB_TURMA(COD_TURMA)

CREATE TABLE TB_FREQUENCIA
  (
    COD_FREQUENCIA   INTEGER IDENTITY CONSTRAINT TB_FREQUENCIA_PK PRIMARY KEY CLUSTERED (COD_FREQUENCIA) NOT NULL ,
    RM_ALUNO         INTEGER CONSTRAINT TB_FREQUENCIA_FK FOREIGN KEY REFERENCES TB_ALUNO(RM_ALUNO) NOT NULL ,
    NM_ALUNO         VARCHAR (120) NOT NULL ,
    NM_TURMA         VARCHAR (20) NOT NULL ,
    NM_MATERIA       VARCHAR (120) NOT NULL ,
    FREQUENCIA_ALUNO VARCHAR (4) NOT NULL ,
    NOTA_ALUNO       VARCHAR (3) NOT NULL ,
    FALTAS_ALUNO     INTEGER NOT NULL ,
   )
GO

CREATE TABLE TB_MATERIA
  (
    COD_MATERIA   INTEGER IDENTITY CONSTRAINT TB_MATERIA_PK PRIMARY KEY NOT NULL ,
    NM_MATERIA    VARCHAR (120) NOT NULL ,
   )
GO

ALTER TABLE TB_MATERIA
ADD
COD_PROFFESSOR INTEGER CONSTRAINT TB_MATERIA_FK FOREIGN KEY REFERENCES TB_PROFESSOR(COD_PROFESSOR)

CREATE TABLE TB_OCORRENCIA
  (
    COD_OCORRENCIA INTEGER IDENTITY CONSTRAINT TB_OCORRENCIA_PK PRIMARY KEY NOT NULL ,
    NUM_OCORRENCIA INTEGER NOT NULL ,
    NM_ALUNO       VARCHAR (120) NOT NULL ,
    RM_ALUNO       INTEGER CONSTRAINT TB_OCORRENCIA_FK FOREIGN KEY REFERENCES TB_ALUNO(RM_ALUNO) NOT NULL ,
    DESCRICAO_OCORRENCIA TEXT ,
  )
GO

ALTER TABLE TB_OCORRENCIA
ADD
COD_PROFESSOR INTEGER CONSTRAINT TB_OCORRENCIA_FK1 FOREIGN KEY REFERENCES TB_PROFESSOR(COD_PROFESSOR)

CREATE TABLE TB_PROFESSOR
  (
    COD_PROFESSOR     INTEGER IDENTITY CONSTRAINT TB_PROFESSOR_PK PRIMARY KEY NOT NULL ,
    NM_PROFESSOR      VARCHAR (120) NOT NULL ,
    SEXO_PROFESSOR    CHAR (1) NOT NULL DEFAULT 'F' ,
    RG_PROFESSOR      VARCHAR (11) NOT NULL ,
    CPF_PROFESSOR     VARCHAR (12) NOT NULL ,
    DT_NASC_PROFESSOR DATE NOT NULL ,
    TEL_PROFESSOR     VARCHAR (10) NOT NULL ,
    CEL_PROFESSOR     VARCHAR (11) ,
    END_PROFESSOR     VARCHAR (120) NOT NULL ,
    EMAIL_PROFESSOR   VARCHAR (120) ,
    CHECK
	(
		SEXO_PROFESSOR IN ('F', 'M')
	)
  )
GO

ALTER TABLE TB_PROFESSOR
ADD 
LOGIN_PROFESSOR   VARCHAR (120) NOT NULL ,
SENHA_PROFESSOR   VARCHAR (120) NOT NULL ,
CSENHA_PROFESSOR  VARCHAR (120) NOT NULL
GO    

CREATE TABLE TB_RESPONSAVEL
  (
    NM_RESPONSAVEL    VARCHAR (120) NOT NULL ,
    COD_RESPONSAVEL   INTEGER CONSTRAINT TB_RESPONSAVEL_PK PRIMARY KEY NOT NULL ,
    TEL_RESPONSAVEL   VARCHAR (10) NOT NULL ,
    CEL_RESPONSAVEL   VARCHAR (11) ,
    EMAIL_RESPONSAVEL VARCHAR (120) ,
    END_RESPONSAVEL   VARCHAR (120) NOT NULL ,
    NM_ALUNO          VARCHAR (120) NOT NULL ,
    RM_ALUNO          INTEGER CONSTRAINT TB_RESPONSAVEL_FK FOREIGN KEY REFERENCES TB_ALUNO(RM_ALUNO) NOT NULL ,
  )
GO

CREATE TABLE TB_TURMA
  (
    COD_TURMA INTEGER CONSTRAINT TB_TURMA_PK PRIMARY KEY NOT NULL ,
    NM_TURMA  VARCHAR (100) NOT NULL ,
  )
GO

CREATE TABLE TB_USUARIO
  (
    COD_USUARIO    INTEGER CONSTRAINT TB_USUARIO_PK PRIMARY KEY NOT NULL ,
    NM_USUARIO     VARCHAR (120) NOT NULL ,
    SENHA_USUARIO  VARCHAR (120) NOT NULL ,
    CSENHA_USUARIO VARCHAR (120) NOT NULL ,
    EMAIL_USUARIO  VARCHAR (120) ,
    TEL_USUARIO   VARCHAR (10) NOT NULL ,
    CEL_USUARIO   VARCHAR (11) ,
    LOGIN_USUARIO VARCHAR (120) NOT NULL ,
  )
GO