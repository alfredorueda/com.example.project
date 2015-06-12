package com.example.project.Model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected Long id;

    @Column
    protected String name;

    @Column
    protected int idCard;

    public Empleado() {
    }

    public Empleado(String name, int idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCard=" + idCard +
                '}';
    }
}
