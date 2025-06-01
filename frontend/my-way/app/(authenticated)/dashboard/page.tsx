"use client";
import React, { useState } from "react";

import { useSession } from "next-auth/react";

import { Roadmap } from "@/components/Roadmap";
import { FloatButton } from "@/components/floatButton";
import { useDisclosure } from "@heroui/react";
import ModalRegisterRoadmap from "@/components/modalRegisterRoadmap";
import { RoadmapType } from "@/types";

export default function DashboardPage() {
  const { data: session, status } = useSession();
  const { isOpen, onOpenChange, onClose, onOpen } = useDisclosure();
  const [roadmapAtual, setRoadmapAtual] = useState<RoadmapType | null>(null);

  if (status === "loading") {
    return <p>Carregando sessão...</p>;
  }

  if (!session) {
    return <p>Usuário não autenticado</p>;
  }

  return (
    <div>
      <Roadmap roadmapAtual={roadmapAtual} setRoadmapAtual={setRoadmapAtual} />
      <FloatButton onClick={onOpen} />
      <ModalRegisterRoadmap
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        setRoadmapAtual={setRoadmapAtual}
        onClose={onClose}
      />
    </div>
  );
}
