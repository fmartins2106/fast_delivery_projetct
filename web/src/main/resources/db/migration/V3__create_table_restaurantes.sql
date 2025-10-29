create table restaurantes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria ENUM ('PIZZA', 'RESTAURANTE', 'HAMBURGUERIA', 'SUSHI', 'DOCERIA', 'PASTELARIA', 'SORVETERIA') NOT NULL,
    id_endereco INT NOT NULL,
    telefone VARCHAR(18) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    hora_abertura TIME NOT NULL,
    hora_fechamento TIME NOT NULL,
    pedido_minimo DECIMAL(7,2) NOT NULL,
    ativo boolean default false,
    CONSTRAINT FK_restaurante_endereco FOREIGN KEY (id_endereco) REFERENCES enderecos(id)
);