# Proyecto Tópicos
Proyecto API REST para la materia Tópicos Avançados
#### Descripción
API que provee métodos para visualizar empleados registrados según su rol y antigüedad en la empresa, así como para hacer un aumento en su salario según rol y antigüedad.

#### Arquitectura
La aplicación posee un Controlador REST y un Repositorio. Están especificados modelos para los empleados (Employee) y para las requisiciones de aumento (Raise).

#### Métodos
**GET "/employees"** retorna todos los empleados  
**GET "/employees/byrole/{role}"** donde {role} es "junior", "middle" o "senior", retorna todos los empleados con ese rol  
**GET "/employees/byspan/{span}"** donde {span} es un lapso con el formato "^\\d+\-\\d+$", retorna todos los empleados cuya antigüedad está dentro de ese rango  
**POST "/employees/post"** recibe un JSON con un determinado formato y aplica el aumento a todos los funcionarios que satisfagan los requisitos de rol y antigüedad.  

**Formato de JSON para POST:**  
{  
	"role": "role",  
	"raise": "n",  
	"years": "n"  
}  
