package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by NRAM on 07/04/14.
 */
@Entity
public class Shift extends Model {

    //Variaveis
    @Id
    @Constraints.Min(10)
    public Long id;

    @ManyToOne
    public Discipline discipline;

    @Constraints.Required
    public String code;

    @Constraints.Required
    public String description;

    @ManyToMany
    @JoinTable(name = "general_user_shift")
    public List<User> students;

    //Construtor
    public Shift() {
    }

    //ToString
    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }

    //DataBase
    public static Finder<Long, Shift> find = new Finder(
            Long.class, Shift.class
    );

    public static List<Shift> all() {
        return find.all();
    }

    public static Shift create(Shift shift, Long id_discipline){
        shift.discipline = Discipline.find.ref(id_discipline);
        shift.save();
        return shift;
    }

    public static Shift getById(Long id){
        return find.where()
                .eq("id", id)
                .findUnique();
    }

    public static void delete(Long id){
        find.ref(id).delete();
    }

    public static void update(Shift shift, Long discipline_id){
        shift.discipline = Discipline.find.ref(discipline_id);
        shift.update();
    }
}
