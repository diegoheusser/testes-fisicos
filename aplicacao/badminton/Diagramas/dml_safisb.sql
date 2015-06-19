LOCK TABLES `protocolo` WRITE;
/*!40000 ALTER TABLE `protocolo` DISABLE KEYS */;
INSERT INTO `protocolo` VALUES ('Agilidade','Agilidade em Quadra',1,'',''),
('Resistência Anaeróbica','Shuttle Run',2,'minutos','Tempo'),
('Flexibilidade','Flexibilidade de Ombro e Punho',3,'polegadas','Distância (polegadas)'),
('Coordenação e Tempo de Reação','Lançamento de Bolas na Parede',5,'lances','Lances (em 30 segundos)'),
('Flexibilidade e Força','Repetição de Agachamento 20',6,'',''),
('Equilíbrio','Teste da Cegonha',8,'segundos','Tempo (segundos)'),
('Força','Musculatura do Core',9,'',''),
('Força','Salto Vertical',10,'Cm','Distância (Cm)'),
('Força','Salto Horizontal',11,'Cm','Distância(Cm)'),
('Agilidade','Teste do Hexágono',12,'segundos','Tempo (milisegundos)'),
('Tempo de Reação','Tempo de Reação Régua',13,'milisegundos','Tempo (milisegundos)'),
('Flexibilidade','Teste de Sentar e Alcançar',15,'Cm','Distância(Cm)');
/*!40000 ALTER TABLE `protocolo` ENABLE KEYS */;
UNLOCK TABLES;
