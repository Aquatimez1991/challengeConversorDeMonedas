package Modelos;

public class FormatoDeConversion {
    private String from;
    private String to;
    private double amount;
    private double result;
    private String fecha;
    private String hora;


    public FormatoDeConversion(String from, String to, double amount, double result, String fecha, String hora) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
        this.fecha = fecha;
        this.hora = hora;
    }


    public String getFrom() { return from; }
    public String getTo() { return to; }
    public double getAmount() { return amount; }
    public double getResult() { return result; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
}
