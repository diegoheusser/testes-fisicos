//AtletaDAO.java
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Atleta;

/**
 * @author 5102011212
 */
public class AtletaDAO extends DAO {

    //método para salvar os atletas no banco de dados
    public void save(Atleta athletes) throws SQLException {
        String insert = "INSERT INTO ATLETA(atletaID, nome, instituicao, estatura, massaCorporal, tempoPratica, "
                + "praticaSemana,nivel,dataNascimento,genero,lateralidade,envergadura,email, historico) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        save(insert, athletes.getId(), athletes.getNome(), athletes.getInstituicao().getId(), athletes.getEstatura(),
                athletes.getMassaCorporal(), athletes.getPratica(), athletes.getPraticaSemana(), athletes.getNivel(),
                athletes.getDataNascimento(), athletes.getGenero(), athletes.getLateralidade(), athletes.getEnvergadura(),
                athletes.getEmail(), athletes.getHistorico());
    }

    //método para alterar os atletas no banco de dados
    public void update(Atleta athletes) throws SQLException {
        String update = "UPDATE ATLETA "
                + "SET nome = ?, instituicao = ?, estatura = ?, massaCorporal = ?, tempoPratica = ?, praticaSemana = ?, "
                + "nivel = ?, dataNascimento = ?, genero = ?, lateralidade = ?, envergadura = ?, email = ?, historico = ? "
                + "WHERE atletaID = ?";
        update(update, athletes.getId(), athletes.getNome(), athletes.getInstituicao().getId(), athletes.getEstatura(),
                athletes.getMassaCorporal(), athletes.getPratica(), athletes.getPraticaSemana(), athletes.getNivel(),
                athletes.getDataNascimento(), athletes.getGenero(), athletes.getLateralidade(), athletes.getEnvergadura(),
                athletes.getEmail(), athletes.getHistorico());
    }

    //método para apagar os atletas do banco dados
    public void delete(int id) throws SQLException {
        String delete = "DELETE FROM ATLETA WHERE ATLETAID = ?";
        delete(delete, id);
    }

    //método que retorna uma lista de todos os atletas cadastrados
    public List<Atleta> atletas() throws SQLException {
        List<Atleta> atleta = new ArrayList<>();
        String select = "SELECT * FROM ATLETA";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Atleta a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            a.setHistorico(rs.getString("historico"));
            atleta.add(a);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return atleta;
    }

    public List<Atleta> atletas(modelo.Instituicao i) throws SQLException {
        List<Atleta> atleta = new ArrayList<>();
        String select = "SELECT * FROM ATLETA WHERE instituicao = " + i.getId() + " ";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Atleta a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            a.setHistorico(rs.getString("historico"));
            atleta.add(a);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return atleta;
    }

    public List<Atleta> atletas(String nome, String genero, String nivel,
            String lateralidade, modelo.Instituicao i) throws SQLException {
        String seleciona = "SELECT * FROM Atleta a "
                + "WHERE a.instituicao = " + i.getId() + " ";
        if (!nome.isEmpty()) {
            seleciona += "AND a.nome LIKE '" + nome + "%' ";
        }
        if (!genero.isEmpty()) {
            seleciona += "AND a.genero = '" + genero + "' ";
        }
        if (!nivel.isEmpty()) {
            seleciona += "AND a.nivel = '" + nivel + "' ";
        }
        if (!lateralidade.isEmpty()) {
            seleciona += "AND a.lateralidade = '" + lateralidade + "' ";
        }
        seleciona += "ORDER BY a.nome ";

        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(seleciona);

        List<Atleta> lista = new ArrayList<>();

        while (rs.next()) {
            Atleta a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(i);
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            a.setHistorico(rs.getString("historico"));
            lista.add(a);
        }
        
        rs.close();
        stmt.close();
        return lista;
    }

    //método que retorna uma lista dos atletas que pertencem a uma determinada instituição
    public List<Atleta> athletesOfTheInstitution(int id) throws SQLException {
        List<Atleta> atleta = new ArrayList<>();
        String select = "SELECT * FROM ATLETA WHERE instituicao = " + id;
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Atleta a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            a.setHistorico(rs.getString("historico"));
            atleta.add(a);
        }

        rs.close();
        stmt.close();
        //fecha a conexão 
        super.connection.close();
        return atleta;
    }

    //método que retorna uma lista de atleta com os seguintes filtros : instituição, gênero, nível
    public List<Atleta> listInstitutionGenderLevel(int institution, String gender, String level) throws SQLException {
        List<Atleta> atleta = new ArrayList<>();
        String select = "SELECT * FROM ATLETA WHERE instituicao = '" + institution + "' and genero = '" + gender + "' and nivel = '" + level + "'";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Atleta a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            a.setHistorico(rs.getString("historico"));
            atleta.add(a);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return atleta;
    }

    //método que retorna uma lista conetendo o atleta que possui um determinado id
    public List<Atleta> findByID(int id) throws SQLException {
        String select = "SELECT * FROM ATLETA WHERE atletaID = " + id + "";
        Atleta atleta = null;
        List<Atleta> a = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            atleta = new Atleta();
            atleta.setId(rs.getInt("atletaID"));
            atleta.setNome(rs.getString("nome"));
            atleta.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            atleta.setEstatura(rs.getDouble("estatura"));
            atleta.setMassaCorporal(rs.getDouble("massaCorporal"));
            atleta.setPratica(rs.getString("tempoPratica"));
            atleta.setPraticaSemana(rs.getString("praticaSemana"));
            atleta.setNivel(rs.getString("nivel"));
            atleta.setDataNascimento(rs.getDate("dataNascimento"));
            atleta.setGenero(rs.getString("genero"));
            atleta.setLateralidade(rs.getString("lateralidade"));
            atleta.setEnvergadura(rs.getInt("envergadura"));
            atleta.setEmail(rs.getString("email"));
            atleta.setHistorico(rs.getString("historico"));
            a.add(atleta);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return a;
    }

    //método que retorna uma lista dos atletas que possuem um determinado nome
    public List<Atleta> searchAthletesByName(String name) throws SQLException {
        String select = "SELECT * FROM ATLETA WHERE nome like'%" + name + "%' ";
        Atleta atleta = null;
        List<Atleta> a = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            atleta = new Atleta();
            atleta.setId(rs.getInt("atletaID"));
            atleta.setNome(rs.getString("nome"));
            atleta.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            atleta.setEstatura(rs.getDouble("estatura"));
            atleta.setMassaCorporal(rs.getDouble("massaCorporal"));
            atleta.setPratica(rs.getString("tempoPratica"));
            atleta.setPraticaSemana(rs.getString("praticaSemana"));
            atleta.setNivel(rs.getString("nivel"));
            atleta.setDataNascimento(rs.getDate("dataNascimento"));
            atleta.setGenero(rs.getString("genero"));
            atleta.setLateralidade(rs.getString("lateralidade"));
            atleta.setEnvergadura(rs.getInt("envergadura"));
            atleta.setEmail(rs.getString("email"));
            atleta.setHistorico(rs.getString("historico"));
            a.add(atleta);
        }
        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return a;
    }

    public Atleta athleteObject(int id) throws SQLException {

        String select = "SELECT * FROM ATLETA WHERE atletaID = " + id + "";
        Atleta atleta = null;
        List<Atleta> a = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            atleta = new Atleta();
            atleta.setId(rs.getInt("atletaID"));
            atleta.setNome(rs.getString("nome"));
            atleta.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            atleta.setEstatura(rs.getDouble("estatura"));
            atleta.setMassaCorporal(rs.getDouble("massaCorporal"));
            atleta.setPratica(rs.getString("tempoPratica"));
            atleta.setPraticaSemana(rs.getString("praticaSemana"));
            atleta.setNivel(rs.getString("nivel"));
            atleta.setDataNascimento(rs.getDate("dataNascimento"));
            atleta.setGenero(rs.getString("genero"));
            atleta.setLateralidade(rs.getString("lateralidade"));
            atleta.setEnvergadura(rs.getInt("envergadura"));
            atleta.setEmail(rs.getString("email"));
            atleta.setHistorico(rs.getString("historico"));
        }

        rs.close();
        stmt.close();
        return atleta;
    }

}
