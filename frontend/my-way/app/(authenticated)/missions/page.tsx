"use client";
import { Card, CardHeader, CardFooter, Button } from "@heroui/react";
import { useSession } from "next-auth/react";
// import { signOut } from "@/auth";
import character1 from "@/public/character1.png";
import Image from "next/image";
import { Empty } from "@/components/empty";

export default function MissionsPage() {
  const { data: session } = useSession();

  //   if (status === "loading") {
  //     return <p>Carregando sessão...</p>;
  //   }

  //   if (!session) {
  //     return <p>Usuário não autenticado</p>;
  //   }

  console.log({ session });

  return (
    <div>
      <h1 className="dark:text-white text-3xl py-4">Missões diárias</h1>
      <div className="max-w-[900px] gap-2 grid grid-cols-12 grid-rows-1">
        <Card
          isFooterBlurred
          className="w-full h-[300px] col-span-12 sm:col-span-5"
        >
          <CardHeader className="absolute z-10 top-1 flex-col items-start">
            <p className="text-tiny text-white/60 uppercase font-bold">New</p>
            <h4 className="text-white font-medium text-2xl">
              Estudar na plataforma
            </h4>
          </CardHeader>
          <Image
            alt="Card example background"
            className="z-0 w-full h-full scale-125 -translate-y-6 object-cover"
            src={character1}
          />
          <CardFooter className="absolute bg-white/30 bottom-0 border-t-1 border-zinc-100/50 z-10 justify-between">
            <div>
              <p className="text-black text-tiny">
                Tempo a estudar: 15 minutos.
              </p>
              <p className="text-black text-tiny">
                Tempo restante para concluir:{" "}
              </p>
            </div>
            <Button
              className="text-tiny"
              color="primary"
              radius="full"
              size="sm"
            >
              Verificar conclusão
            </Button>
          </CardFooter>
        </Card>
      </div>
      <h1 className="dark:text-white text-3xl py-4">Missões semanais</h1>
      <Empty>Não há missões semanais</Empty>
      <h1 className="dark:text-white text-3xl py-4">Missões concluídas</h1>
      <Empty>Não há missões concluídas</Empty>
    </div>
  );
}
