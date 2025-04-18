"use client";
import React from "react";
import { Form, Input, Checkbox, Button } from "@heroui/react";
import { title } from "@/components/primitives";
import clsx from "clsx";

type ErrorsType = {
  name?: string;
  email?: string;
  password?: string;
  terms?: string;
};

export default function RegisterPage() {
  const [password, setPassword] = React.useState("");
  const [submitted, setSubmitted] = React.useState<{
    [key: string]: FormDataEntryValue;
  } | null>(null);
  const [errors, setErrors] = React.useState<ErrorsType>({});

  const getPasswordError = (value: any) => {
    if (value.length < 4) {
      return "Password must be 4 characters or more";
    }
    if ((value.match(/[A-Z]/g) || []).length < 1) {
      return "Password needs at least 1 uppercase letter";
    }
    if ((value.match(/[^a-z]/gi) || []).length < 1) {
      return "Password needs at least 1 symbol";
    }

    return null;
  };

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.currentTarget));

    const newErrors: ErrorsType = {};

    const passwordError = getPasswordError(data.password);

    if (passwordError) {
      newErrors.password = passwordError;
    }

    if (data.name === "admin") {
      newErrors.name = "Nice try! Choose a different username";
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);

      return;
    }

    if (data.terms !== "true") {
      setErrors({ terms: "Please accept the terms" });

      return;
    }

    setErrors({});
    setSubmitted(data);
  };

  return (
    <Form
      className="w-full justify-center items-center space-y-4"
      validationErrors={errors}
      onReset={() => setSubmitted(null)}
      onSubmit={onSubmit}
    >
      <h1 className={clsx(title(), "dark:text-white")}>Sign up</h1>
      <div className="flex flex-col gap-4 max-w-md">
        <Input
          isRequired
          errorMessage={({ validationDetails }) => {
            if (validationDetails.valueMissing) {
              return "Please enter your name";
            }

            return errors.name;
          }}
          label="Name"
          labelPlacement="outside"
          name="name"
          placeholder="Enter your name"
        />

        <Input
          isRequired
          errorMessage={({ validationDetails }) => {
            if (validationDetails.valueMissing) {
              return "Please enter your email";
            }
            if (validationDetails.typeMismatch) {
              return "Please enter a valid email address";
            }
          }}
          label="Email"
          labelPlacement="outside"
          name="email"
          placeholder="Enter your email"
          type="email"
        />

        <Input
          isRequired
          errorMessage={getPasswordError(password)}
          isInvalid={getPasswordError(password) !== null}
          label="Password"
          labelPlacement="outside"
          name="password"
          placeholder="Enter your password"
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
          I agree to the terms and conditions
        </Checkbox>

        {errors.terms && (
          <span className="text-danger text-small">{errors.terms}</span>
        )}

        <div className="flex gap-4">
          <Button className="w-full" color="primary" type="submit">
            Submit
          </Button>
          <Button type="reset" variant="bordered">
            Reset
          </Button>
        </div>
      </div>

      {submitted && (
        <div className="text-small text-default-500 mt-4">
          Submitted data: <pre>{JSON.stringify(submitted, null, 2)}</pre>
        </div>
      )}
    </Form>
  );
}
