import React, { useState } from "react";

import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
  DrawerFooter,
  Button,
  Divider,
} from "@heroui/react";
import { GoalType, StudyTopicType } from "@/types";
import axios from "axios";
import { LoadingSpinner } from "../loadingSpinner";

type ModalWithMessageProps = {
  goalId: number;
  title?: string;
  description?: string;
  isOpen?: boolean;
  onOpenChange?: (open: boolean) => void;
  setGoals: React.Dispatch<React.SetStateAction<GoalType[] | undefined>>;
  exercices?: StudyTopicType[];
};

export default function GoalDescription({
  goalId,
  title,
  description,
  isOpen,
  onOpenChange,
  exercices,
  setGoals,
}: ModalWithMessageProps) {
  const [loading, setLoading] = useState(false);
  const [studyTopics, setStudyTopics] = useState<StudyTopicType[]>(
    exercices || []
  );

  const cancelarExercicio = async (exerciceId: number) => {
    try {
      setLoading(true);
      const data = await axios.post(
        `http://localhost:8081/studytopic/cancel/${exerciceId}`
      );

      setStudyTopics((prev) =>
        prev.map((exercice) =>
          exercice.id === exerciceId
            ? { ...exercice, status: "CANCELLED" }
            : exercice
        )
      );

    } catch (error) {
      console.error("Erro ao cancelar exercício:", error);
    } finally {
      setLoading(false);
    }
  };

  const concluirExercicio = async (exerciceId: number) => {
    try {
      setLoading(true);
      const data = await axios.post(
        `http://localhost:8081/studytopic/finish/${exerciceId}`
      );

      setStudyTopics((prev) =>
        prev.map((exercice) =>
          exercice.id === exerciceId
            ? { ...exercice, status: "CONCLUDED" }
            : exercice
        )
      );

      if (verificarTodosStudyTopicsConcluidos(exerciceId)) {
        await axios.post(
          `http://localhost:8081/goal/finish-goal/${goalId}`
        );

        setGoals((prev) =>
          prev
            ? prev.map((goal: GoalType) =>
                goal.id === goalId ? { ...goal, status: "CONCLUDED" } : goal
              )
            : prev
        );
      }
    } catch (error) {
      console.error("Erro ao concluir exercício:", error);
    } finally {
      setLoading(false);
    }
  };

  const excluirTarefa = async () => {
    try {
      setLoading(true);
      const data = await axios.post(
        `http://localhost:8081/goal/cancel-goal/${goalId}`
      );

      setGoals((prev) =>
        prev
          ? prev.map((goal: GoalType) =>
              goal.id === goalId ? { ...goal, status: "CANCELLED" } : goal
            )
          : prev
      );

    } catch (error) {
      console.error("Erro ao excluir tarefa:", error);
    } finally {
      setLoading(false);
    }
  };

  const verificarTodosStudyTopicsConcluidos = (
    idExercicioConcluidoAgora: number
  ) => {
    let todosConcluidos = true;

    studyTopics.forEach((exercice) => {
      if (
        exercice.id !== idExercicioConcluidoAgora &&
        exercice.status === "ACTIVE"
      ) {
        todosConcluidos = false;
      }
    });

    return todosConcluidos;
  };

  return (
    <>
      <Drawer isOpen={isOpen} onOpenChange={onOpenChange} placement="left">
        {loading && <LoadingSpinner />}
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1 dark:text-white text-xl">
                {title}
              </DrawerHeader>
              <Divider />
              <DrawerBody className="dark:text-white">
                <p className="text-xl">Descrição:</p>
                <p>{description}</p>
                <Divider />
                <p className="text-xl">Exercícios:</p>
                {studyTopics?.map((exercice, index) => (
                  <div
                    key={index}
                    className="flex justify-between items-center"
                  >
                    <span className="mr-4">{exercice.name}</span>
                    <div className="flex gap-2">
                      {exercice.status === "ACTIVE" && [
                        <Button
                          key="buttonExcluir"
                          color="danger"
                          onPress={() => cancelarExercicio(exercice.id)}
                        >
                          Excluir
                        </Button>,
                        <Button
                          key="buttonConcluir"
                          color="success"
                          onPress={() => concluirExercicio(exercice.id)}
                        >
                          Concluir
                        </Button>,
                      ]}
                      {exercice.status === "CONCLUDED" && (
                        <Button color="success" disabled>
                          Concluído
                        </Button>
                      )}
                      {exercice.status === "CANCELLED" && (
                        <Button color="danger" disabled>
                          Cancelado
                        </Button>
                      )}
                    </div>
                  </div>
                ))}
              </DrawerBody>
              <DrawerFooter>
                <Button
                  color="danger"
                  variant="light"
                  onPress={async () => {
                    await excluirTarefa();
                    onClose();
                  }}
                >
                  Excluir tarefa
                </Button>
                <Button color="primary" onPress={onClose}>
                  Fechar detalhes
                </Button>
              </DrawerFooter>
            </>
          )}
        </DrawerContent>
      </Drawer>
    </>
  );
}
