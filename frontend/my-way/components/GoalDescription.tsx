import React from "react";

import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerBody,
  DrawerFooter,
  Button,
  Divider,
} from "@heroui/react";
import { StudyTopicType } from "@/types";

type ModalWithMessageProps = {
  title?: string;
  description?: string;
  isOpen?: boolean;
  onOpenChange?: (open: boolean) => void;
  exercices?: StudyTopicType[];
};

export default function GoalDescription({
  title,
  description,
  isOpen,
  onOpenChange,
  exercices,
}: ModalWithMessageProps) {
  return (
    <>
      <Drawer isOpen={isOpen} onOpenChange={onOpenChange} placement="left">
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1 dark:text-white text-xl">
                {title}
              </DrawerHeader>
              <Divider/>
              <DrawerBody className="dark:text-white">
                <p className="text-xl">Descrição:</p>
                <p>{description}</p>
                <Divider />
                <p className="text-xl">Exercícios:</p>
                {exercices?.map((exercice, index) => (
                  <div
                    key={index}
                    className="flex justify-between items-center"
                  >
                    <span className="mr-4">{exercice.name}</span>
                    <div className="flex gap-2">
                      <Button color="danger">Excluir</Button>
                      <Button color="success">Concluir</Button>
                    </div>
                  </div>
                ))}
              </DrawerBody>
              <DrawerFooter>
                <Button color="danger" variant="light" onPress={onClose}>
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
