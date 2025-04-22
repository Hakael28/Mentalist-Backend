/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mt.mentalist.modelo.Enum;

import lombok.Getter;

@Getter
public enum Etapa {
    PRIMERA_INFANCIA("Primer Infancia"),
    INFANCIA("Infancia"),
    ADOLESCENCIA("Adolescencia"),
    JUVENTUD("Juventud"),
    ADULTEZ("Adultez"),
    VEJEZ("Vejez");
    
    private final String etapaLegible;
    Etapa(String etapaLegible){
        this.etapaLegible=etapaLegible;
    }
    
    @Override
    public String toString(){
        return etapaLegible;
    }
}
