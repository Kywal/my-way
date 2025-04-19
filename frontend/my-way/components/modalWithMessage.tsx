import {
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Button,
} from "@heroui/react";

type ModalWithMessageProps = {
  title?: string;
  message?: string;
  actionText?: string;
  onActionPress?: () => void;
  isOpen?: boolean;
  onOpenChange?: (open: boolean) => void;
};

export default function ModalWithMessage({
  title,
  message,
  actionText,
  isOpen,
  onOpenChange,
  onActionPress,
}: ModalWithMessageProps) {
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
                {title}
              </ModalHeader>
              <ModalBody>{message}</ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Fechar
                </Button>
                <Button
                  color="primary"
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
