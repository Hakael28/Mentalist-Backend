/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mt.mentalist.modelo.Enum;

/**
 *
 * @author jisaw
 */
public enum TipoDiagnostico {
    VIOLENCIA_DE_GENERO("Violencia de género"), 
    TRASTORNO_MENTAL("Trastorno mental"),
    INTENTO_DE_SUICIDIO("Intento de suicidio"),
    CONSUMO_DE_SUSTANCIAS("Consumo de sustancias");
    
    private final String tipoDiagnosticoLegible;
    TipoDiagnostico(String tipoDiagnosticoLegible){
        this.tipoDiagnosticoLegible=tipoDiagnosticoLegible;
    }
    
    @Override
    public String toString(){
        return tipoDiagnosticoLegible;
    }

}
