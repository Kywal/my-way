import React, { useState } from "react";

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

export const Roadmap = () => {
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

  return (
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
            items={listGoals.map((goal) => goal.id)}
            strategy={verticalListSortingStrategy} 
          >
            {listGoals.map((goal, index) => (
              <div
                key={goal.id}
                className={`flex justify-center ${index % 2 === 0 ? "self-start" : "self-end"}`}
              >
                <SortableItem key={goal.id} id={goal.id}>
                  <Goal
                    title={goal.title}
                    description={goal.description}
                    exercices={goal.exercices}
                  />
                </SortableItem>
              </div>
            ))}
          </SortableContext>
        </DndContext>
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
