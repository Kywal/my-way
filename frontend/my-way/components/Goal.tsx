import React from "react";
import {
  Card,
  CardHeader,
  CardBody,
  Divider,
  Button,
  useDisclosure,
} from "@heroui/react";
import Image from "next/image";

import mywaylogo from "@/public/my-way-logo.png";
import GoalDescription from "./GoalDescription";
import { StudyTopicType } from "@/types";

interface GoalProps {
  title: string;
  description: string;
  exercices: StudyTopicType[];
}

export const Goal = ({ title, description, exercices }: GoalProps) => {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  return (
    <Card className="max-w-[400px]">
      <CardHeader className="flex gap-3">
        <Image
          alt="heroui logo"
          height={40}
          src={mywaylogo}
          width={40}
          className="rounded-lg"
        />
        <div className="flex flex-row-reverse justify-between w-full">
          <Button onPress={onOpen}>Ações</Button>

          <div className="flex flex-col">
            <p className="text-md">{title}</p>
            <p className="text-small text-default-500">
              {exercices.length} exercícios
            </p>
          </div>
        </div>
      </CardHeader>
      <Divider />
      <CardBody>
        <p>{description}</p>
      </CardBody>
      <GoalDescription
        title="Ações disponíveis para essa tarefa"
        description={description}
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        exercices={exercices}
      />
    </Card>
  );
};
