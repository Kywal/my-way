"use client";
import React from "react";

import { useSession } from "next-auth/react";

import { Roadmap } from "@/components/Roadmap";

export default function DashboardPage() {
  const { data: session, status } = useSession();

  if (status === "loading") {
    return <p>Carregando sessão...</p>;
  }

  if (!session) {
    return <p>Usuário não autenticado</p>;
  }

  return (
    <div>
      <Roadmap />
    </div>
  );
}
