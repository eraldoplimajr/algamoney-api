CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(200) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero VARCHAR(20) NOT NULL,
	complemento VARCHAR(100),
	bairro VARCHAR(50) NOT NULL,
	cep VARCHAR(20) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	estado VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Eraldo', true, 'Av. Boa Viagem', '100', 'Bl-A', 'Boa Viagem', '55555-555', 'Recife', 'Pernambuco');
INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Junior', false, 'Av. Agamenom Magalhães', '50', 'Centro', '55444-444', 'Caruaru', 'Pernambuco');