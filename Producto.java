package es.aplicaciones.alvaro.entrelazadas;

/**
 * Created by Álvaro on 28/08/2015.
 */
public class Producto {

    private String Id;
    private String Referencia;
    private String Nombre;
    private String Categoria;
    private String SubFamilia;
    private String Familia;
    private String Descripcion;
    private String Precio;
    private String Image1;
    private String Image2;
    private String Image3;
    private String Image4;
    private String Image5;


    public Producto (String Id ,String Referencia,String Nombre, String Categoria, String Subfamilia ,
                     String Familia, String Descripcion, String Precio, String Image1, String Image2,
                     String Image3,String Image4,String Image5){
        this.Id          = Id;
        this.Referencia  = Referencia;
        this.Nombre      = Nombre;
        this.Categoria   = Categoria;
        this.SubFamilia  = Subfamilia;
        this.Familia     = Familia;
        this.Descripcion = Descripcion;
        this.Precio      = Precio;
        this.Image1      = Image1;
        this.Image2      = Image2;
        this.Image3      = Image3;
        this.Image4      = Image4;
        this.Image5      = Image5;
    }
    public Producto (Producto objeto) {
        new Producto(objeto.Id, objeto.Referencia, objeto.Nombre, objeto.Categoria, objeto.SubFamilia,
                objeto.Familia, objeto.Descripcion, objeto.Precio, objeto.Image1, objeto.Image2,
                objeto.Image3, objeto.Image4, objeto.Image5);
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getSubFamilia() {
        return SubFamilia;
    }

    public void setSubFamilia(String subFamilia) {
        this.SubFamilia = subFamilia;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }
    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

    public String getImage4() {
        return Image4;
    }

    public void setImage4(String image4) {
        Image4 = image4;
    }

    public String getImage5() {
        return Image5;
    }

    public void setImage5(String image5) {
        Image5 = image5;
    }
}
