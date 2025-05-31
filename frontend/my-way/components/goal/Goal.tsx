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
import { GoalType, StudyTopicType } from "@/types";

interface GoalProps {
  id: number;
  title: string;
  description: string;
  exercices: StudyTopicType[];
  status: string;
  setGoals: React.Dispatch<React.SetStateAction<GoalType[] | undefined>>;
}

export const Goal = ({
  id,
  title,
  description,
  exercices,
  status,
  setGoals,
}: GoalProps) => {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();

  const statusClass =
    {
      ACTIVE: null,
      CANCELLED: "bg-red-600",
      CONCLUDED: "bg-green-600",
    }[status] || null;

  return (
    <Card className={`max-w-[450px] min-w-[300px] ${statusClass}`}>
      <CardHeader className="flex gap-3">
        <Image
          alt="heroui logo"
          height={40}
          src={mywaylogo}
          width={40}
          className="rounded-lg"
        />
        <div className="flex flex-row-reverse justify-between w-full gap-2">
          {status === "CANCELLED" && (
            <div className="text-white font-bold">Cancelado</div>
          )}
          {status === "CONCLUDED" && (
            <div className="text-white font-bold">Concluído</div>
          )}
          {status === "ACTIVE" && <Button onPress={onOpen}>Ações</Button>}

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
        goalId={id}
        title="Ações disponíveis para essa tarefa"
        description={description}
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        exercices={exercices}
        setGoals={setGoals}
      />
    </Card>
  );
};
