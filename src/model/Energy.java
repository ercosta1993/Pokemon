
package model;


public class Energy  extends Card{
    private TypeElement element;
    
    public Energy(TypeCard tipo, TypeElement element) {
        super(tipo);
        this.element = element;
    }

    public TypeElement getElement() {
        return element;
    }

    public void setElement(TypeElement element) {
        this.element = element;
    }
    
    @Override
    public String toString(){
        StringBuilder msg = new StringBuilder();
        msg.append(element);
        return msg.toString();
    }
}