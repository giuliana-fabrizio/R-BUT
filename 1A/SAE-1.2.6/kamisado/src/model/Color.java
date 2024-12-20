package model;

public class Color {
    protected String nameColor;
    protected String codeHexa; // à modifier en nom de couleur utilisé par jfx(truc en MAJ)

    public Color(String nameColor, String codeHexa){
        this.codeHexa=codeHexa;
        this.nameColor=nameColor;
    }

    public String getNameColor() {
        return this.nameColor;
    }

    public String getCodeHexa() {
        return this.codeHexa;
    }

    @Override
    public String toString() {
        return "Color{" +
                "nameColor='" + nameColor + '\'' +
                ", codeHexa='" + codeHexa + '\'' +
                '}';
    }
}

