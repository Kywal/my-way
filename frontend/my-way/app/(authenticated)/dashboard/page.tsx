"use client";
import { Button } from "@heroui/button";
import { useSession } from "next-auth/react";
// import { signOut } from "@/auth";

import { signOut } from "next-auth/react";

export default function DashboardPage() {
  const { data: session, status } = useSession();

  if (status === "loading") {
    return <p>Carregando sessão...</p>;
  }

  if (!session) {
    return <p>Usuário não autenticado</p>;
  }

  console.log({ session });

  return (
    <div>
      <h1 className="dark:text-white">Bem-vindo</h1>
      <Button color="danger" onClick={() => signOut()}>
        Logout
      </Button>
    </div>
  );
}
