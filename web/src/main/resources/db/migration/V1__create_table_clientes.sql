create table clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100) not null,
    telefone VARCHAR(18) not null,
    cpf VARCHAR(11) UNIQUE not null,
    ativo boolean default true not null
);

