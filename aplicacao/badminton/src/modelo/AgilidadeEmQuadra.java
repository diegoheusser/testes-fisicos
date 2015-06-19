package modelo;

/**
 * @author Diego Heusser
 */
public class AgilidadeEmQuadra extends Execucao {

    private String tempo;
    private int parcial;
    private Valido valido;
    private int agilidadeEmQuadraID;
    private String motivo;

    public Valido getValido() {
        return valido;
    }

    public void setValido(Valido valido) {
        this.valido = valido;
    }

    public int getAgilidadeEmQuadraID() {
        return agilidadeEmQuadraID;
    }

    public void setAgilidadeEmQuadraID(int agilidadeEmQuadraID) {
        this.agilidadeEmQuadraID = agilidadeEmQuadraID;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getParcial() {
        return parcial;
    }

    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


}
