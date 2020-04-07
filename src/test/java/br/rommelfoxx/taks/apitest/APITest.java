package br.rommelfoxx.taks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";	
	}
	
	@Test
	public void devoRetonarTarefas() {
		RestAssured.given()
			.log().all()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}
	
	@Test
	public void devoAdicionarTarefasComSucesso() {
		RestAssured.given()
		.body("{\"task\":\"Teste via Api\", \"dueDate\":\"2020-12-30\"}")
		.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log() .all()
			.statusCode(201)
		;
	}
	
	@Test
	public void devoAdicionarTarefasInvalida() {
		RestAssured.given()
		.body("{\"task\":\"Teste via Api\", \"dueDate\":\"2010-12-30\"}")
		.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log() .all()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
		
	}
	
}

