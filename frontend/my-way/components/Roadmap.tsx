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
import { GoalType, RoadmapType } from "@/types";
import { LoadingSpinner } from "./loadingSpinner";

interface RoadmapProps {
  roadmapAtual: RoadmapType | null;
  setRoadmapAtual: (roadmap: any) => void;
}

export const Roadmap = ({ roadmapAtual, setRoadmapAtual }: RoadmapProps) => {
  const [listGoals, setListGoals] = useState<GoalType[]>();
  const [loading, setLoading] = useState(false);
  const sensors = useSensors(
    useSensor(PointerSensor),
    useSensor(KeyboardSensor, {
      coordinateGetter: sortableKeyboardCoordinates,
    })
  );

  const { data: session } = useSession();

  useEffect(() => {
    const fetchRoadmap = async () => {
      if (session && session.user && "id" in session.user) {
        console.log("User ID:", session.user.id);

        try {
          const data = await axios.get(
            `http://localhost:8081/roadmap/find-active/${session.user.id}`
          );

          console.log("Roadmap data:", data);
          setRoadmapAtual(data.data);
          setListGoals(data.data.goals);
        } catch (error) {
          console.log(error);
        }
      }
    };

    fetchRoadmap();
  }, []);

  useEffect(() => {
    if (roadmapAtual) {
      setListGoals(roadmapAtual.goals);
    }
  }, [roadmapAtual]);

  function handleDragEnd(event: any) {
    const { active, over } = event;
    const activeId = Number(active.id);
    const overId = Number(over.id);

    if (!listGoals) return;

    try {
      setLoading(true);
      const oldIndex = listGoals.findIndex((item) => item.id === activeId);
      const newIndex = listGoals.findIndex((item) => item.id === overId);

      axios.post(
        `http://localhost:8081/goal/change-goal-index/${roadmapAtual?.id}`,
        [
          { id: listGoals[oldIndex].id, updatedPosition: newIndex + 1 },
          { id: listGoals[newIndex].id, updatedPosition: oldIndex + 1 },
        ]
      );
    } catch (error) {
      console.error("Error during drag end:", error);

      return;
    } finally {
      setLoading(false);
    }

    if (active.id !== over.id) {
      setListGoals((items) => {
        if (!items) return items;

        console.log({ active, over });

        const oldIndex = items.findIndex((item) => item.id === activeId);
        const newIndex = items.findIndex((item) => item.id === overId);

        return arrayMove(items, oldIndex, newIndex);
      });
    }
  }

  return roadmapAtual && listGoals ? (
    <div className="grid grid-cols-1 gap-4">
      {loading && <LoadingSpinner />}
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
            items={listGoals.map((goal) => String(goal.id))}
            strategy={verticalListSortingStrategy}
          >
            {listGoals.map((goal, index) => (
              <div
                key={goal.id}
                className={`flex justify-center ${index % 2 === 0 ? "self-start" : "self-end"}`}
              >
                <SortableItem key={goal.id} id={String(goal.id)}>
                  <Goal
                    id={goal.id}
                    title={goal.name}
                    description={goal.description}
                    exercices={goal.studyTopics}
                    status={goal.status}
                    setGoals={setListGoals}
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
