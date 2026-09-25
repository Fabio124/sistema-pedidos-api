    package com.pedidos.sistema_pedidos_api.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;

    @Entity
    @Table(name = "cliente")
    public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "El nombre es obligatorio")
        @Column(nullable = false)
        private String nombre;

        @NotBlank(message = "La cédula es obligatoria")
        @Column(nullable = false, unique = true)
        private String cedula;

        @NotBlank(message = "El teléfono es obligatorio")
        @Column(nullable = false)
        private String telefono;

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        @Column(nullable = false, unique = true)
        private String correo;

        public Cliente() {
        }

        public Cliente(String nombre, String cedula, String telefono, String correo) {
            this.nombre = nombre;
            this.cedula = cedula;
            this.telefono = telefono;
            this.correo = correo;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
    }