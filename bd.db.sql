BEGIN TRANSACTION;
CREATE TABLE "Utilizador" (
	`Nickname`	TEXT NOT NULL UNIQUE,
	`Avatar`	TEXT,
	`Email`	TEXT,
	`Password`	TEXT,
	`IBAN`	TEXT
);
CREATE TABLE `Recurrência` (
	`TempoDeRecurrência`	INTEGER NOT NULL,
	`DataLimite`	TEXT NOT NULL
);
CREATE TABLE "Pagamento" (
	`Data`	TEXT,
	`Valor`	INTEGER NOT NULL,
	`Modo`	INTEGER,
	`Credor`	INTEGER NOT NULL,
	`Devedor`	INTEGER NOT NULL
);
CREATE TABLE `Modalidade` (
	`Nome`	TEXT UNIQUE
);
CREATE TABLE `GrupoUtilizador` (
	`Utilizador`	INTEGER NOT NULL,
	`Grupo`	INTEGER NOT NULL
);
CREATE TABLE "Grupo" (
	`Nome`	TEXT NOT NULL UNIQUE,
	`Moderador`	INTEGER NOT NULL
);
CREATE TABLE `DespesaUtilizador` (
	`RácioPagamentoFeito`	INTEGER NOT NULL,
	`RácioPagamentoDevido`	INTEGER NOT NULL,
	`Utilizador`	INTEGER NOT NULL,
	`Despesa`	INTEGER NOT NULL
);
CREATE TABLE "Despesa" (
	`Descrição`	TEXT,
	`Valor`	REAL NOT NULL,
	`Responsável`	INTEGER NOT NULL,
	`DataCriação`	TEXT NOT NULL,
	`DataDespesa`	INTEGER,
	`Recurrência`	INTEGER
);
COMMIT;
