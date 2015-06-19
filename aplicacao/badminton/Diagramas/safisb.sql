SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
CREATE SCHEMA IF NOT EXISTS `badminton` DEFAULT CHARACTER SET latin1 ;
USE `mydb` ;
USE `badminton` ;

-- -----------------------------------------------------
-- Table `badminton`.`agenda`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`agenda` (
  `agendaID` INT(11) NOT NULL AUTO_INCREMENT ,
  `data` DATETIME NOT NULL ,
  `local` VARCHAR(50) NOT NULL ,
  `situacao` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`agendaID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`instituicao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`instituicao` (
  `cidade` VARCHAR(50) NOT NULL ,
  `cnpj` CHAR(18) NOT NULL ,
  `email` VARCHAR(50) NULL DEFAULT NULL ,
  `instituicaoID` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `rua` VARCHAR(50) NOT NULL ,
  `telefone` VARCHAR(50) NOT NULL ,
  `uf` VARCHAR(50) NOT NULL ,
  `cep` VARCHAR(15) NULL DEFAULT NULL ,
  PRIMARY KEY (`instituicaoID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`atleta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`atleta` (
  `atletaID` INT(11) NOT NULL AUTO_INCREMENT ,
  `dataNascimento` DATE NOT NULL ,
  `email` VARCHAR(50) NULL DEFAULT NULL ,
  `envergadura` INT(11) NOT NULL ,
  `estatura` DOUBLE NOT NULL ,
  `genero` VARCHAR(50) NOT NULL ,
  `lateralidade` VARCHAR(50) NOT NULL ,
  `massaCorporal` DOUBLE NOT NULL ,
  `nivel` VARCHAR(50) NOT NULL ,
  `nome` VARCHAR(50) NOT NULL ,
  `praticaSemana` CHAR(5) NOT NULL ,
  `tempoPratica` CHAR(5) NOT NULL ,
  `instituicao` INT(11) NULL DEFAULT NULL ,
  `historico` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`atletaID`) ,
  INDEX `FK_Instituicao_Atleta_idx` (`instituicao` ASC) ,
  CONSTRAINT `FK_Instituicao_Atleta`
    FOREIGN KEY (`instituicao` )
    REFERENCES `badminton`.`instituicao` (`instituicaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`protocolo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`protocolo` (
  `tipo` VARCHAR(60) NOT NULL ,
  `nome` VARCHAR(50) NOT NULL ,
  `protocoloID` INT(11) NOT NULL ,
  `unidade` VARCHAR(20) NULL DEFAULT NULL ,
  `parametro` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`protocoloID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`execucao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`execucao` (
  `execucaoID` INT(11) NOT NULL AUTO_INCREMENT ,
  `atletaID` INT(11) NOT NULL ,
  `protocoloID` INT(11) NOT NULL ,
  `hora` TIME NOT NULL ,
  `data` DATE NOT NULL ,
  `temperatura` INT(11) NOT NULL ,
  `tipoSuperficie` VARCHAR(50) NOT NULL ,
  `status` SMALLINT(1) NOT NULL ,
  PRIMARY KEY (`execucaoID`) ,
  INDEX `agendaID` (`atletaID` ASC) ,
  INDEX `fk_Execucao_Atleta_idx` (`atletaID` ASC) ,
  INDEX `fk_Execucao_Protocolo_idx` (`protocoloID` ASC) ,
  CONSTRAINT `fk_Execucao_Atleta`
    FOREIGN KEY (`atletaID` )
    REFERENCES `badminton`.`atleta` (`atletaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Execucao_Protocolo`
    FOREIGN KEY (`protocoloID` )
    REFERENCES `badminton`.`protocolo` (`protocoloID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 73
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`agendaexecucao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`agendaexecucao` (
  `agendaID` INT(11) NOT NULL ,
  `execucaoID` INT(11) NOT NULL ,
  PRIMARY KEY (`agendaID`, `execucaoID`) ,
  INDEX `fk_AgendaExecucao_Agenda_idx` (`agendaID` ASC) ,
  INDEX `fk_AgendaExecucao_Execucao_idx` (`execucaoID` ASC) ,
  CONSTRAINT `fk_AgendaExecucao_Agenda`
    FOREIGN KEY (`agendaID` )
    REFERENCES `badminton`.`agenda` (`agendaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AgendaExecucao_Execucao`
    FOREIGN KEY (`execucaoID` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`agilidadeemquadra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`agilidadeemquadra` (
  `agilidadeEmQuadraID` INT(11) NOT NULL AUTO_INCREMENT ,
  `tempo` VARCHAR(10) NOT NULL ,
  `parcial` INT(11) NOT NULL ,
  `motivo` VARCHAR(200) NULL DEFAULT NULL ,
  `valido` INT(11) NOT NULL ,
  `execucaoID` INT(11) NOT NULL ,
  PRIMARY KEY (`agilidadeEmQuadraID`) ,
  INDEX `FK_AgilidadeEmQuadra_Execucao` (`execucaoID` ASC) ,
  CONSTRAINT `FK_AgilidadeEmQuadra_Execucao`
    FOREIGN KEY (`execucaoID` )
    REFERENCES `badminton`.`execucao` (`execucaoID` ))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`atletaagendado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`atletaagendado` (
  `atletaAgendadoId` INT(11) NOT NULL ,
  `atleta` INT(11) NOT NULL ,
  `ordem` INT(11) NOT NULL ,
  PRIMARY KEY (`atletaAgendadoId`, `atleta`) ,
  INDEX `fk_atletaAgendado_atleta_idx` (`atleta` ASC) ,
  INDEX `fk_atletaAgendado_Agenda_idx` (`atletaAgendadoId` ASC) ,
  CONSTRAINT `fk_atletaAgendado_Agenda`
    FOREIGN KEY (`atletaAgendadoId` )
    REFERENCES `badminton`.`agenda` (`agendaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atletaAgendado_atleta`
    FOREIGN KEY (`atleta` )
    REFERENCES `badminton`.`atleta` (`atletaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`cancelamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`cancelamento` (
  `cancelamentoID` INT(11) NOT NULL ,
  `execucaoID` INT(11) NOT NULL ,
  PRIMARY KEY (`cancelamentoID`) ,
  INDEX `execucaoID` (`execucaoID` ASC) ,
  CONSTRAINT `FK_Cancelamento_Execucao`
    FOREIGN KEY (`execucaoID` )
    REFERENCES `badminton`.`execucao` (`execucaoID` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`cegonha`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`cegonha` (
  `cegonha_id` INT(11) NOT NULL ,
  `tempo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`cegonha_id`) ,
  INDEX `FK_Cegonha_Execucao_idx` (`cegonha_id` ASC) ,
  CONSTRAINT `FK_Cegonha_Execucao`
    FOREIGN KEY (`cegonha_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`flexibilidade_de_ombro_e_punho`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`flexibilidade_de_ombro_e_punho` (
  `flexibilidade_de_ombro_e_punho_id` INT(11) NOT NULL AUTO_INCREMENT ,
  `distancia` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`flexibilidade_de_ombro_e_punho_id`) ,
  INDEX `FK_FOP_execucao_idx` (`flexibilidade_de_ombro_e_punho_id` ASC) ,
  CONSTRAINT `FK_FOP_execucao`
    FOREIGN KEY (`flexibilidade_de_ombro_e_punho_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`grupo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`grupo` (
  `grupoID` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `instituicaoID` INT(11) NOT NULL ,
  `dataInicial` DATE NOT NULL ,
  `dataFinal` DATE NOT NULL ,
  PRIMARY KEY (`grupoID`) ,
  INDEX `instituicaoID` (`instituicaoID` ASC) ,
  CONSTRAINT `fkInstituicao`
    FOREIGN KEY (`instituicaoID` )
    REFERENCES `badminton`.`instituicao` (`instituicaoID` ))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`grupoatleta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`grupoatleta` (
  `atletaID` INT(11) NOT NULL ,
  `grupoID` INT(11) NOT NULL ,
  `grupoAtletaID` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`grupoAtletaID`) ,
  INDEX `atletaID` (`atletaID` ASC) ,
  INDEX `grupoID` (`grupoID` ASC) ,
  CONSTRAINT `fkAtleta`
    FOREIGN KEY (`atletaID` )
    REFERENCES `badminton`.`atleta` (`atletaID` ),
  CONSTRAINT `fkGrupo`
    FOREIGN KEY (`grupoID` )
    REFERENCES `badminton`.`grupo` (`grupoID` ))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`hexagono`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`hexagono` (
  `hexagono_id` INT(11) NOT NULL ,
  `tempo` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`hexagono_id`) ,
  INDEX `FK_Hexagono_Execuao_idx` (`hexagono_id` ASC) ,
  CONSTRAINT `FK_Hexagono_Execucao`
    FOREIGN KEY (`hexagono_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`laf`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`laf` (
  `lafID` INT(11) NOT NULL ,
  `laf` INT(11) NOT NULL ,
  PRIMARY KEY (`lafID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`lancamento_de_bolas_na_parede`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`lancamento_de_bolas_na_parede` (
  `lancamento_de_bolas_na_parede_id` INT(11) NOT NULL ,
  `quantidade` INT(11) NOT NULL ,
  PRIMARY KEY (`lancamento_de_bolas_na_parede_id`) ,
  INDEX `FK_LDBNP_Execuao_idx` (`lancamento_de_bolas_na_parede_id` ASC) ,
  CONSTRAINT `FK_LDBNP_Execuao`
    FOREIGN KEY (`lancamento_de_bolas_na_parede_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`motivo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`motivo` (
  `motivoID` INT(11) NOT NULL ,
  `cancelamentoID` INT(11) NOT NULL ,
  `motivo` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`motivoID`) ,
  INDEX `cancelamentoID` (`cancelamentoID` ASC) ,
  CONSTRAINT `FK_Motivo_Cancelamento`
    FOREIGN KEY (`cancelamentoID` )
    REFERENCES `badminton`.`cancelamento` (`cancelamentoID` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`musculatura_do_core`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`musculatura_do_core` (
  `musculatura_do_core_id` INT(11) NOT NULL ,
  `completo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`musculatura_do_core_id`) ,
  INDEX `FK_MDC_Execucao_idx` (`musculatura_do_core_id` ASC) ,
  CONSTRAINT `FK_MDC_Execucao`
    FOREIGN KEY (`musculatura_do_core_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`protocoloagendado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`protocoloagendado` (
  `protocoloAgendadoId` INT(11) NOT NULL DEFAULT '0' ,
  `protocolo` INT(11) NOT NULL ,
  `ordem` INT(11) NOT NULL ,
  `status` TINYINT(4) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`protocoloAgendadoId`, `protocolo`) ,
  INDEX `fk_protocoloAgendado_Protocolo_idx` (`protocolo` ASC) ,
  INDEX `fk_protocoloAgendado_Agenda_idx` (`protocoloAgendadoId` ASC) ,
  CONSTRAINT `fk_protocoloAgendado_Agenda`
    FOREIGN KEY (`protocoloAgendadoId` )
    REFERENCES `badminton`.`agenda` (`agendaID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_protocoloAgendado_Protocolo`
    FOREIGN KEY (`protocolo` )
    REFERENCES `badminton`.`protocolo` (`protocoloID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`repeticao_de_agachamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`repeticao_de_agachamento` (
  `repeticao_de_agachamento_id` INT(11) NOT NULL ,
  `quantidade` INT(11) NOT NULL ,
  PRIMARY KEY (`repeticao_de_agachamento_id`) ,
  INDEX `FK_RDA_Execucao_idx` (`repeticao_de_agachamento_id` ASC) ,
  CONSTRAINT `FK_RDA_Execucao`
    FOREIGN KEY (`repeticao_de_agachamento_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`salto_horizontal`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`salto_horizontal` (
  `salto_horizontal_id` INT(11) NOT NULL ,
  `distancia` INT(11) NOT NULL ,
  PRIMARY KEY (`salto_horizontal_id`) ,
  INDEX `FK_Salto_Horizontal_Execucao_idx` (`salto_horizontal_id` ASC) ,
  CONSTRAINT `FK_Salto_Horizontal_Execucao`
    FOREIGN KEY (`salto_horizontal_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`salto_vertical`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`salto_vertical` (
  `salto_vertical_id` INT(11) NOT NULL ,
  `distancia` INT(11) NOT NULL ,
  PRIMARY KEY (`salto_vertical_id`) ,
  INDEX `FK_Salto_Vertical_Execucao_idx` (`salto_vertical_id` ASC) ,
  CONSTRAINT `FK_Salto_Vertical_Execucao`
    FOREIGN KEY (`salto_vertical_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`sentar_alcancar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`sentar_alcancar` (
  `sentar_alcancar_id` INT(11) NOT NULL ,
  `distancia` INT(11) NOT NULL ,
  PRIMARY KEY (`sentar_alcancar_id`) ,
  INDEX `FK_Sentar_Alcancar_Execucao_idx` (`sentar_alcancar_id` ASC) ,
  CONSTRAINT `FK_Sentar_Alcancar_Execucao`
    FOREIGN KEY (`sentar_alcancar_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`shuttlerun`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`shuttlerun` (
  `shuttleRunID` INT(11) NOT NULL AUTO_INCREMENT ,
  `nivel` INT(11) NOT NULL ,
  `tempo` VARCHAR(50) NOT NULL ,
  `execucaoID` INT(11) NOT NULL ,
  PRIMARY KEY (`shuttleRunID`) ,
  INDEX `fk_ShuttleRun_Execucao_idx` (`execucaoID` ASC) ,
  CONSTRAINT `fk_ShuttleRun_Execucao`
    FOREIGN KEY (`execucaoID` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `badminton`.`tempo_reacao_regua`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `badminton`.`tempo_reacao_regua` (
  `tempo_reacao_regua_id` INT(11) NOT NULL ,
  `milisegundos` INT(11) NOT NULL ,
  PRIMARY KEY (`tempo_reacao_regua_id`) ,
  INDEX `FK_Tempo_reacao_regua_Execucao_idx` (`tempo_reacao_regua_id` ASC) ,
  CONSTRAINT `FK_Tempo_reacao_regua_Execucao`
    FOREIGN KEY (`tempo_reacao_regua_id` )
    REFERENCES `badminton`.`execucao` (`execucaoID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
