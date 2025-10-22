create table enderecos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(100) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(30),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    uf VARCHAR(2) NOT NULL
);