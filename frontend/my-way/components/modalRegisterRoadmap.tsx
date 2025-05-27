import React, { useState } from "react";

import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
  Input,
  Form,
} from "@heroui/react";

type ModalRegisterRoadmapProps = {
  actionText?: string;
  onActionPress?: () => void;
  isOpen?: boolean;
  onOpenChange?: (open: boolean) => void;
};

type ErrorsType = {
  objetivoPrincipal?: string;
  descricaoObjetivo?: string;
};

export default function ModalRegisterRoadmap({
  actionText,
  isOpen,
  onOpenChange,
  onActionPress,
}: ModalRegisterRoadmapProps) {
  const [errors, setErrors] = useState<ErrorsType>({});

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.currentTarget));

    const newErrors: ErrorsType = {};

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);

      return;
    }

    console.log({ data });
  };

  return (
    <>
      <Modal
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        className="dark:text-white"
      >
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1 text-center">
                Gerar novo roadmap
              </ModalHeader>
              <ModalBody>
                <Form
                  className="w-full justify-center items-center space-y-4"
                  validationErrors={errors}
                  onSubmit={onSubmit}
                >
                  <Input
                    isRequired
                    errorMessage={({ validationDetails }) => {
                      if (validationDetails.valueMissing) {
                        return "Por favor, insira o objetivo principal";
                      }
                    }}
                    label="Objetivo Principal"
                    labelPlacement="outside"
                    name="objetivo"
                    placeholder="Aprender Java Backend"
                    type="text"
                    className="mb-4"
                  />

                  <Input
                    isRequired
                    label="Descrição do Objetivo"
                    labelPlacement="outside"
                    name="description"
                    placeholder="Desejo aprender a programar em Java e me tornar um desenvolvedor backend"
                    type="text"
                  />
                </Form>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Fechar
                </Button>
                <Button
                  color="primary"
                  type="submit"
                  onPress={() => {
                    if (onActionPress) onActionPress();
                    onClose();
                  }}
                >
                  {actionText ? actionText : "OK"}
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </>
  );
}
