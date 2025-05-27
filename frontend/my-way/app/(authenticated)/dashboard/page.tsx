"use client";
import React from "react";

import { useSession } from "next-auth/react";

import { Roadmap } from "@/components/Roadmap";
import { FloatButton } from "@/components/floatButton";
import { useDisclosure } from "@heroui/react";
import ModalRegisterRoadmap from "@/components/modalRegisterRoadmap";

export default function DashboardPage() {
  const { data: session, status } = useSession();
  const { isOpen, onOpenChange, onClose, onOpen } = useDisclosure();

  if (status === "loading") {
    return <p>Carregando sessão...</p>;
  }

  if (!session) {
    return <p>Usuário não autenticado</p>;
  }

  return (
    <div>
      <Roadmap />
      <FloatButton onClick={onOpen} />
      <ModalRegisterRoadmap isOpen={isOpen} onOpenChange={onOpenChange} />
    </div>
  );
}
