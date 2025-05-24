"use client";
import React, { useEffect } from "react";
import { Form, Input, Button, useDisclosure } from "@heroui/react";
import { title } from "@/components/primitives";
import clsx from "clsx";
import { UserLogin } from "@/types";
import ModalWithMessage from "@/components/modalWithMessage";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";

type ErrorsType = {
  email?: string;
  password?: string;
};

export default function LoginPage() {
  const [password, setPassword] = React.useState("");
  const [errors, setErrors] = React.useState<ErrorsType>({});
  const [isSubmitting, setIsSubmitting] = React.useState(false);
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [onSuccessLogin, setOnSuccessLogin] = React.useState(false);
  const [progressValueWithSuccess, setProgressValueWithSuccess] =
    React.useState(0);

  const router = useRouter();

  useEffect(() => {
    let interval: NodeJS.Timeout;

    if (onSuccessLogin)
      interval = setInterval(() => {
        if (progressValueWithSuccess >= 100) {
          clearInterval(interval);
        } else setProgressValueWithSuccess((v) => v + 2);
      }, 60);

    return () => clearInterval(interval);
  }, [onSuccessLogin]);

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsSubmitting(true);
    const data = Object.fromEntries(new FormData(e.currentTarget));

    const newErrors: ErrorsType = {};

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setIsSubmitting(false);

      return;
    }

    const user: UserLogin = {
      email: data.email as string,
      password: data.password as string,
    };

    const res = await signIn("credentials", {
      redirect: true,
      email: user.email,
      password: user.password,
      callbackUrl: "/dashboard",
    });

    setErrors({});
    setIsSubmitting(false);
    setOnSuccessLogin(true);
  };

  return (
    <Form
      className="w-full justify-center items-center space-y-4"
      validationErrors={errors}
      onSubmit={onSubmit}
    >
      <h1 className={clsx(title(), "dark:text-white")}>Login</h1>
      <div className="flex flex-col gap-4 max-w-md min-w-60">
        <Input
          isRequired
          errorMessage={({ validationDetails }) => {
            if (validationDetails.valueMissing) {
              return "Por favor, insira seu email";
            }
            if (validationDetails.typeMismatch) {
              return "Por favor, insira um endereço de email válido";
            }
          }}
          label="Email"
          labelPlacement="outside"
          name="email"
          placeholder="Insira seu email"
          type="email"
        />

        <Input
          isRequired
          label="Senha"
          labelPlacement="outside"
          name="password"
          placeholder="Insira sua senha"
          type="password"
          value={password}
          onValueChange={setPassword}
        />

        <div className="flex gap-4">
          <Button
            className="w-full"
            color="primary"
            type="submit"
            isLoading={isSubmitting}
          >
            Login
          </Button>
        </div>
      </div>
      <ModalWithMessage
        title="Erro ao realizar login"
        message="Erro ao tentar fazer login no sistema, tente novamente."
        isOpen={isOpen}
        onOpenChange={onOpenChange}
      />
    </Form>
  );
}
