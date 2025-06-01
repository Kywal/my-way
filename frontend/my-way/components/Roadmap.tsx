import React, { useEffect, useState } from "react";

import {
  arrayMove,
  SortableContext,
  sortableKeyboardCoordinates,
  useSortable,
  verticalListSortingStrategy,
} from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { Goal } from "./Goal";
import {
  closestCenter,
  DndContext,
  KeyboardSensor,
  PointerSensor,
  useSensor,
  useSensors,
} from "@dnd-kit/core";
import { useSession } from "next-auth/react";
import axios from "axios";
import { ArrowDownRightIcon } from "./icons";
import { RoadmapType } from "@/types";

interface RoadmapProps {
  roadmapAtual: RoadmapType | null;
  setRoadmapAtual: (roadmap: any) => void;
}

export const Roadmap = ({ roadmapAtual, setRoadmapAtual }: RoadmapProps) => {
  const [listGoals, setListGoals] = useState<
    { id: string; title: string; description: string; exercices: string[] }[]
  >([
    {
      id: "0",
      title: "Aprender Spring Boot",
      description:
        "Aprender o básico do spring boot, como estrutura e arquitetura.",
      exercices: ["teste", "teste2", "teste3"],
    },
    {
      id: "1",
      title: "Aprender autenticação básica",
      description:
        "Aprender autenticação básica, como login e cadastro de usuários.",
      exercices: ["teste", "teste2"],
    },
    {
      id: "2",
      title: "Aprender consultas SQL",
      description: "Aprender sobre consultas SQL, queries e mais.",
      exercices: ["teste", "teste2"],
    },
  ]);
  const sensors = useSensors(
    useSensor(PointerSensor),
    useSensor(KeyboardSensor, {
      coordinateGetter: sortableKeyboardCoordinates,
    })
  );

  const { data: session } = useSession();

  useEffect(() => {
    console.log("Session:", session);
    const fetchRoadmap = async () => {
      if (session && session.user && "id" in session.user) {
        console.log("User ID:", session.user.id);

        try {
          const data = await axios.get(
            `http://localhost:8081/roadmap/find-active/${session.user.id}`
          );

          console.log("Roadmap data:", data);
          setRoadmapAtual(data.data);
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchRoadmap();
  }, []);

  function handleDragEnd(event: any) {
    const { active, over } = event;

    if (active.id !== over.id) {
      setListGoals((items) => {
        const oldIndex = items.findIndex((item) => item.id === active.id);
        const newIndex = items.findIndex((item) => item.id === over.id);

        return arrayMove(items, oldIndex, newIndex);
      });
    }
  }


  return roadmapAtual ? (
    <div className="grid grid-cols-1 gap-4">
      <h1 className="dark:text-white text-3xl py-4 text-center">
        Roadmap atual: Aprender Java Backend
      </h1>

      <div className="flex flex-col gap-4 px-[20%]">
        <DndContext
          sensors={sensors}
          collisionDetection={closestCenter}
          onDragEnd={handleDragEnd}
        >
          <SortableContext
            items={roadmapAtual.goals.map((goal) => String(goal.id))}
            strategy={verticalListSortingStrategy}
          >
            {roadmapAtual.goals.map((goal, index) => (
              <div
                key={goal.id}
                className={`flex justify-center ${index % 2 === 0 ? "self-start" : "self-end"}`}
              >
                <SortableItem key={String(goal.id)} id={String(goal.id)}>
                  <Goal
                    title={goal.name}
                    description={goal.description}
                    exercices={goal.studyTopics}
                  />
                </SortableItem>
              </div>
            ))}
          </SortableContext>
        </DndContext>
      </div>
    </div>
  ) : (
    <div>
      <div>
        <h1 className="dark:text-white text-3xl py-4 text-center">
          Nenhum roadmap ativo encontrado
        </h1>
        <p className="text-center dark:text-gray-400">
          Você pode criar um novo roadmap clicando no botão abaixo.
        </p>
      </div>
      <div className="">
        <ArrowDownRightIcon className="text-white fill-white w-[180px] h-[180px] absolute bottom-10 right-10" />
      </div>
    </div>
  );
};

function SortableItem({
  id,
  children,
}: {
  id: string;
  children: React.ReactNode;
}) {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id });

  const style = {
    transform: CSS.Transform.toString(transform),
    transition,
  };

  return (
    <div ref={setNodeRef} style={style} {...attributes} {...listeners}>
      {children}
    </div>
  );
}
