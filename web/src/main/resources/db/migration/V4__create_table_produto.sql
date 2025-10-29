create table produtos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) not null,
    preco DECIMAL(7,2) not null,
    id_restaurante INT NOT NULL,
    CONSTRAINT FK_produto_restaurante FOREIGN KEY (id_restaurante) REFERENCES restaurantes(id)
);

