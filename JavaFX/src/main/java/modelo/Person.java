package modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

    private final StringProperty Nombres;
    private final StringProperty Apellidos;
    private final StringProperty Calle;
    private final IntegerProperty CodPostal;
    private final StringProperty Ciudad;
    private final ObjectProperty<LocalDate> Cumple;


    public Person() {
        this(null, null);
    }


    public Person(String firstName, String lastName) {
        this.Nombres = new SimpleStringProperty(firstName);
        this.Apellidos = new SimpleStringProperty(lastName);


        this.Calle = new SimpleStringProperty("some street");
        this.CodPostal = new SimpleIntegerProperty(1234);
        this.Ciudad= new SimpleStringProperty("some city");
        this.Cumple = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return Nombres.get();
    }

    public void setFirstName(String firstName) {
        this.Nombres.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return Nombres;
    }

    public String getLastName() {
        return Apellidos.get();
    }

    public void setLastName(String lastName) {
        this.Apellidos.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return Apellidos;
    }

    public String getStreet() {
        return Calle.get();
    }

    public void setStreet(String street) {
        this.Calle.set(street);
    }

    public StringProperty streetProperty() {
        return Calle;
    }

    public int getPostalCode() {
        return CodPostal.get();
    }

    public void setPostalCode(int postalCode) {
        this.CodPostal.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return CodPostal;
    }

    public String getCity() {
        return Ciudad.get();
    }

    public void setCity(String city) {
        this.Ciudad.set(city);
    }

    public StringProperty cityProperty() {
        return Ciudad;
    }

    public LocalDate getBirthday() {
       // return Cumple.get();
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        public LocalDate getBirthday() {
            return birthday.get();
        }
    }

    public void setBirthday(LocalDate birthday) {
        this.Cumple.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return Cumple;
    }


}