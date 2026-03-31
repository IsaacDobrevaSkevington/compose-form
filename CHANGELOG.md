# [1.0.0](https://github.com/IsaacDobrevaSkevington/compose-form/releases/tag/1.0.0)

## New Features

- `LocalFormBox` added to avoid extension functions
- FormState added to allow disable of entire form and submission observation

## Breaking changes

- Many of the default form boxes now use different signatures for composable parameters

## Compatibility

| Library | Compiled against | Tested up to |
|---------|------------------|--------------|
| kotlin     | 2.3.20           | 2.3.20       |
| compose | 1.10.2          | 1.10.3        |    

---

**Full Changelog**: https://github.com/IsaacDobrevaSkevington/compose-form/compare/0.0.9...1.0.0

---

# [0.0.9](https://github.com/IsaacDobrevaSkevington/compose-form/releases/tag/0.0.9)

## New Features

- Form extensions via `FormExtension` and `UntypedFormExtension`
- FormController accessible via `LocalFormController`, `withLocalFormController` and `withUntypedLocalFormController`

## Bug Fixes

- `@ComposeValidator`, `@Regex` and `@Validator` were not Repeatable in `:reflect`

## Breaking changes

None

## Compatibility

| Library | Compiled against | Tested up to |
|---------|------------------|--------------|
| kotlin  | 2.2.21           | 2.3.0        |
| compose | 1.9.3            | 1.10.0       |    

---