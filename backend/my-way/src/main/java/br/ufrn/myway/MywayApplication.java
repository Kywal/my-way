//package br.ufrn.myway;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.Scanner;
//
//@SpringBootApplication(scanBasePackages = "br.ufrn.myway")
//public class MywayApplication {
//
//	public static void main(String[] args) {
//
//
//		RoadmapType appType = RoadmapType.INVALID;
//
//		while(appType.equals(RoadmapType.INVALID)) {
//			appType = runConfigCLI();
//		}
//
//		if (appType.equals(RoadmapType.GENERALISTA)) {
//			app.setAdditionalProfiles("Generalista");
//		} else if (appType.equals(RoadmapType.POLICIA_CIVIL)) {
//			app.setAdditionalProfiles("Policia-civil");
//		}
//
//		else if (appType.equals(RoadmapType.PROFESSOR)) {
//
//		}
//
//		app.run(args);
//	}
//
//	private static RoadmapType runConfigCLI() {
//		System.out.println(menuInterface);
//		Scanner scanner = new Scanner(System.in);
//		int appType = scanner.nextInt();
//
//		return switch (appType) {
//			case 1 -> RoadmapType.GENERALISTA;
//			case 2 -> RoadmapType.POLICIA_CIVIL;
//			case 3 -> RoadmapType.PROFESSOR;
//			default -> RoadmapType.INVALID;
//		};
//	}
//
//	static final String menuInterface =
//				"""
//				MENU DE CONFIGURAÇÃO +*+
//				--Configure a aplicação que será gerada!
//				(1) -> Concurso Generalista;
//				(2) -> Concurso para Polícia Civil;
//				(3) -> Concurso para Professr;
//				""";
//	}
//
//enum RoadmapType {
//	GENERALISTA,
//	POLICIA_CIVIL,
//	PROFESSOR,
//	INVALID
//}
