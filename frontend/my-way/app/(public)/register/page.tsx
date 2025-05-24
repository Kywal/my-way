"use client";
import React, { useEffect } from "react";
import {
  Form,
  Input,
  Checkbox,
  Button,
  Progress,
  Link,
  useDisclosure,
} from "@heroui/react";
import { title } from "@/components/primitives";
import clsx from "clsx";
import axios from "axios";
import { UserRegister } from "@/types";
import ModalWithMessage from "@/components/modalWithMessage";
import { CheckCircleIcon } from "@/components/icons";

type ErrorsType = {
  name?: string;
  email?: string;
  password?: string;
  terms?: string;
};

export default function RegisterPage() {
  const [password, setPassword] = React.useState("");
  const [errors, setErrors] = React.useState<ErrorsType>({});
  const [isSubmitting, setIsSubmitting] = React.useState(false);
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [onSuccessRegister, setOnSuccessRegister] = React.useState(false);
  const [progressValueWithSuccess, setProgressValueWithSuccess] =
    React.useState(0);

  useEffect(() => {
    let interval: NodeJS.Timeout;

    if (onSuccessRegister)
      interval = setInterval(() => {
        if (progressValueWithSuccess >= 100) {
          clearInterval(interval);
        } else setProgressValueWithSuccess((v) => v + 2);
      }, 60);

    return () => clearInterval(interval);
  }, [onSuccessRegister]);

  const getPasswordError = (value: any) => {
    if (value.length < 4) {
      return "A senha precisa de pelo menos 4 caracteres";
    }
    if ((value.match(/[A-Z]/g) || []).length < 1) {
      return "A senha precisa de pelo menos 1 letra maiúscula";
    }
    if ((value.match(/[^a-z]/gi) || []).length < 1) {
      return "A senha precisa de pelo menos 1 símbolo";
    }

    return null;
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    setIsSubmitting(true);
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.currentTarget));

    const newErrors: ErrorsType = {};

    const passwordError = getPasswordError(data.password);

    if (passwordError) {
      newErrors.password = passwordError;
    }

    if (data.name === "admin") {
      newErrors.name = "Boa tentativa! Escolha um nome diferente";
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      setIsSubmitting(false);

      return;
    }

    if (data.terms !== "true") {
      setErrors({ terms: "Por favor, leia e aceite os termos" });
      setIsSubmitting(false);

      return;
    }

    const user: UserRegister = {
      email: data.email as string,
      password: data.password as string,
      person: {
        name: data.name as string,
      },
    };

    await createUser(user);
    setErrors({});
    setIsSubmitting(false);
    setOnSuccessRegister(true);
  };

  const createUser = async (user: UserRegister) => {
    try {
      const response = await axios.post(
        "http://localhost:8081/user/create",
        user
      );

      return response;
    } catch (error) {
      setIsSubmitting(false);
      onOpen();
    }
  };

  return onSuccessRegister ? (
    <div className="flex flex-col items-center justify-center">
      {progressValueWithSuccess >= 100 ? (
        <div className="flex flex-col items-center justify-center">
          <h1 className={clsx(title(), "dark:text-white")}>
            Cadastro realizado com sucesso!
          </h1>
          <p className="text-center text-small dark:text-white">
            Agora você pode logar no sistema com seu email e senha.{" "}
            <Link href="/login">Entrar Agora</Link>
          </p>
          <CheckCircleIcon className="text-success mt-6" size={150} />
        </div>
      ) : (
        <Progress
          aria-label="Progresso do usuário sendo criado"
          className="max-w-md dark:text-white mb-8"
          color="success"
          showValueLabel={progressValueWithSuccess < 100}
          size="md"
          value={progressValueWithSuccess}
        />
      )}
    </div>
  ) : (
    <Form
      className="w-full justify-center items-center space-y-4"
      validationErrors={errors}
      onSubmit={onSubmit}
    >
      <h1 className={clsx(title(), "dark:text-white")}>Cadastrar</h1>
      <div className="flex flex-col gap-4 max-w-md">
        <Input
          isRequired
          errorMessage={({ validationDetails }) => {
            if (validationDetails.valueMissing) {
              return "Por favor, insira seu nome";
            }

            return errors.name;
          }}
          label="Nome"
          labelPlacement="outside"
          name="name"
          placeholder="Insira seu nome"
        />

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
          errorMessage={getPasswordError(password)}
          isInvalid={getPasswordError(password) !== null}
          label="Senha"
          labelPlacement="outside"
          name="password"
          placeholder="Insira sua senha"
          type="password"
          value={password}
          onValueChange={setPassword}
        />

        <Checkbox
          isRequired
          classNames={{
            label: "text-small",
          }}
          isInvalid={!!errors.terms}
          name="terms"
          validationBehavior="aria"
          value="true"
          onValueChange={() =>
            setErrors((prev) => ({ ...prev, terms: undefined }))
          }
        >
          Aceito os termos e condições
        </Checkbox>

        {errors.terms && (
          <span className="text-danger text-small">{errors.terms}</span>
        )}

        <div className="flex gap-4">
          <Button
            className="w-full"
            color="primary"
            type="submit"
            isLoading={isSubmitting}
          >
            Cadastrar
          </Button>
          <Button type="reset" variant="bordered">
            Resetar
          </Button>
        </div>
      </div>
      <ModalWithMessage
        title="Erro ao cadastrar"
        message="Erro ao tentar cadastrar, tente novamente."
        isOpen={isOpen}
        onOpenChange={onOpenChange}
      />
    </Form>
  );
}
