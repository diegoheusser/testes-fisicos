package modelo;

import java.awt.Color;
import java.util.Date;
import java.util.Objects;

/**
 * @author Diego Heusser
 */
public class Atleta {

    private int id, envergadura;
    private String nome, pratica, praticaSemana, nivel, genero,
            lateralidade, email, historico;
    private double estatura, massaCorporal;
    private Date dataNascimento;
    private String tempo;
    private Color cor;
    
    private Instituicao instituicao;

    public Atleta() {
        cor = Color.white;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(int envergadura) {
        this.envergadura = envergadura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPratica() {
        return pratica;
    }

    public void setPratica(String pratica) {
        this.pratica = pratica;
    }

    public String getPraticaSemana() {
        return praticaSemana;
    }

    public void setPraticaSemana(String praticaSemana) {
        this.praticaSemana = praticaSemana;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLateralidade() {
        return lateralidade;
    }

    public void setLateralidade(String lateralidade) {
        this.lateralidade = lateralidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getMassaCorporal() {
        return massaCorporal;
    }

    public void setMassaCorporal(double massaCorporal) {
        this.massaCorporal = massaCorporal;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.envergadura;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.pratica);
        hash = 79 * hash + Objects.hashCode(this.praticaSemana);
        hash = 79 * hash + Objects.hashCode(this.nivel);
        hash = 79 * hash + Objects.hashCode(this.genero);
        hash = 79 * hash + Objects.hashCode(this.lateralidade);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.historico);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.estatura) ^ (Double.doubleToLongBits(this.estatura) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.massaCorporal) ^ (Double.doubleToLongBits(this.massaCorporal) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.dataNascimento);
        hash = 79 * hash + Objects.hashCode(this.tempo);
        hash = 79 * hash + Objects.hashCode(this.cor);
        hash = 79 * hash + Objects.hashCode(this.instituicao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atleta other = (Atleta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.envergadura != other.envergadura) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.pratica, other.pratica)) {
            return false;
        }
        if (!Objects.equals(this.praticaSemana, other.praticaSemana)) {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.lateralidade, other.lateralidade)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.historico, other.historico)) {
            return false;
        }
        if (Double.doubleToLongBits(this.estatura) != Double.doubleToLongBits(other.estatura)) {
            return false;
        }
        if (Double.doubleToLongBits(this.massaCorporal) != Double.doubleToLongBits(other.massaCorporal)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        if (!Objects.equals(this.tempo, other.tempo)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.instituicao, other.instituicao)) {
            return false;
        }
        return true;
    }

 }
